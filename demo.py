from flask import Flask, request, jsonify
from langchain.llms import GooglePalm
import google.generativeai as palm
from langchain_experimental.sql import SQLDatabaseChain
from langchain.embeddings import HuggingFaceEmbeddings
from langchain.vectorstores import Chroma
from langchain.prompts import SemanticSimilarityExampleSelector, PromptTemplate, FewShotPromptTemplate
from urllib.parse import quote
from langchain.utilities import SQLDatabase

app = Flask(__name__)

# Configure GooglePalm
api_key = "AIzaSyBKitIoRm0Q7dl5ia5kg0Oemos2ClQ7kU0"
palm.configure(api_key=api_key)
llm = GooglePalm(google_api_key=api_key, temperature=0.2)

# Configure SQL Database
db_user = "root"
db_password = "pujitha@2004"
db_host = "localhost"
db_name = "atliq_tshirts"
encoded_password = quote(db_password)

# Construct the connection string
db = SQLDatabase.from_uri(f"mysql+pymysql://{db_user}:{encoded_password}@{db_host}/{db_name}",
                          sample_rows_in_table_info=3)

# Configure embeddings
embeddings = HuggingFaceEmbeddings(model_name='sentence-transformers/all-MiniLM-L6-v2')

# Define db_chain
db_chain = SQLDatabaseChain.from_llm(llm, db, verbose=True)

# Example few shots for the prompt template
few_shots = [
    {'Question': "How many t-shirts do we have left for Nike in XS size and white color?",
     'SQLQuery': "SELECT stock_quantity FROM t_shirts WHERE brand = 'Nike' AND color = 'White' AND size = 'XS'",
     'SQLResult': "Result of the SQL query",
     'Answer': "Result of the SQL query"},
    {'Question': "How much is the total price of the inventory for all S-size t-shirts?",
     'SQLQuery': "select sum(price*stock_quantity) from t_shirts where size = 'S'",
     'SQLResult': "Result of the SQL query",
     'Answer': "Result of the SQL query"},
    {'Question': "If we have to sell all the Levi’s T-shirts today with discounts applied, how much revenue will our store generate (post discounts)?",
     'SQLQuery': """select sum((price*stock_quantity)*(1-ifnull((pct_discount/100),0))) from t_shirts t left join discounts d on t.t_shirt_id = d.t_shirt_id where brand = 'Levi'""",
     'SQLResult': "Result of the SQL query",
     'Answer': "Result of the SQL query"},
    {'Question': "If we have to sell all the Levi’s T-shirts today, how much revenue will our store generate without discount?",
     'SQLQuery': "SELECT SUM(price * stock_quantity) FROM t_shirts WHERE brand = 'Levi'",
     'SQLResult': "Result of the SQL query",
     'Answer': "Result of the SQL query"},
    {'Question': "How many white color Levi's shirts do I have?",
     'SQLQuery': "select sum(stock_quantity) from t_shirts where color = 'white' and brand = 'Levi'",
     'SQLResult': "Result of the SQL query",
     'Answer': "Result of the SQL query"},
    {'Question': "How much sales amount will be generated if we sell all large size t-shirts today in Nike brand after discounts?",
     'SQLQuery': """select sum((price*stock_quantity)*(1-ifnull((pct_discount/100),0))) from t_shirts t left join discounts d on t.t_shirt_id = d.t_shirt_id where brand = 'Nike' and size = 'L'""",
     'SQLResult': "Result of the SQL query",
     'Answer': "Result of the SQL query"}
]

# Define _mysql_prompt and PROMPT_SUFFIX
_mysql_prompt = "Translate the following natural language query to SQL and execute it."
PROMPT_SUFFIX = "Return the result in plain text."

@app.route('/')
def index():
    return "Flask server is running!"

@app.route('/query', methods=['POST'])
def query():
    data = request.json
    query = data.get('query')
    
    # Process query and generate response
    try:
        # Run SQL query
        result = db_chain.run(query)
        
        # Create vectorstore
        to_vectorize = [" ".join(str(value) for value in example.values()) for example in few_shots]
        vectorstore = Chroma.from_texts(to_vectorize, embedding=embeddings, metadatas=few_shots)
        
        # Configure example selector
        example_selector = SemanticSimilarityExampleSelector(
            vectorstore=vectorstore,
            k=2,
        )
        example_selector.select_examples({"Question": query})
        
        # Create few shot prompt template
        example_prompt = PromptTemplate(
            input_variables=["Question", "SQLQuery", "SQLResult", "Answer"],
            template="\nQuestion: {Question}\nSQLQuery: {SQLQuery}\nSQLResult: {SQLResult}\nAnswer: {Answer}",
        )
        
        few_shot_prompt = FewShotPromptTemplate(
            example_selector=example_selector,
            example_prompt=example_prompt,
            prefix=_mysql_prompt,
            suffix=PROMPT_SUFFIX,
            input_variables=["input", "table_info", "top_k"],
        )
        
        # Generate response using the prompt
        new_chain = SQLDatabaseChain.from_llm(llm, db, verbose=True, prompt=few_shot_prompt)
        response = new_chain.run(query)
        
        return jsonify({'reply': response})
    
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, use_reloader=False, port=8000)
