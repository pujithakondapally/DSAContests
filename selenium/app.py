from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time 

driver = webdriver.Chrome()
driver.get("https://www.flipkart.com/search?q=formal%20tops&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off")

content = driver.find_elements(By.CLASS_NAME, 'WKTcLC')
time.sleep(5)
for idx, item in enumerate(content, start=1):
    item.click()
    time.sleep(5)
    print(f"{idx}. {item.text.strip()}\n")
    driver.switch_to.window(driver.window_handles[-1])
    add_to_cart = driver.find_element(By.CSS_SELECTOR, '.QqFHMw.vslbG\\+.In9uk2')
    add_to_cart.click()
    time.sleep(5)
    break
driver.close()


# from selenium import webdriver
# from selenium.webdriver.common.by import By
# from selenium.webdriver.support.ui import WebDriverWait
# from selenium.webdriver.support import expected_conditions as EC
# import time

# driver = webdriver.Chrome()
# driver.get("https://www.flipkart.com/search?q=formal%20tops")

# try:
#     wait = WebDriverWait(driver, 10)

#     # Wait for product elements to load
#     wait.until(EC.presence_of_element_located((By.CLASS_NAME, 'WKTcLC')))
#     content = driver.find_elements(By.CLASS_NAME, 'WKTcLC')

#     if content:
#         first_product = content[0]
#         first_product.click()  # Opens product page

#         # Wait for new content to load (could be in new tab or same tab)
#         time.sleep(3)

#         # If it opened in new tab, switch to it
#         if len(driver.window_handles) > 1:
#             driver.switch_to.window(driver.window_handles[1])

#         # Wait for the add to cart button to appear
#         add_to_cart = wait.until(EC.element_to_be_clickable(
#             (By.CSS_SELECTOR, '.QqFHMw.vslbG\\+.In9uk2')
#         ))
#         add_to_cart.click()
#         print("Add to Cart button clicked successfully!")

# except Exception as e:
#     print("Error:", e)

# finally:
#     time.sleep(3)
#     driver.quit()
