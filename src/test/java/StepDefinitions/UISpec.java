package StepDefinitions;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// This is UI Testing Project 
public class UISpec {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Software\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Given("^User Navigates to Homepage$")
    public void userNavigatesToHomepage() {
      
        driver.get("https://reqres.in");
    }

    @When("User click GET LIST USERS button")
    public void user_click_get_list_users_button() {
    	try {
            WebElement buttonElement1 = driver.findElement(By.xpath("//li[@class='active']"));
            buttonElement1.click();
                        
    	}    	
       catch (NoSuchElementException e) {
    	   System.out.println("button not found");
    	   throw new RuntimeException("Button not found."); 
        }
    }

    @Then("User should see sample1 request and response")
    public void user_should_see_sample1_request_and_response() {       
    	try {
    		
    		WebElement linkElement = driver.findElement(By.xpath("//a[@class='link try-link']"));
    		String hrefValue = linkElement.getAttribute("href");
    		WebElement responseCodeElement = driver.findElement(By.xpath("//span[@class='response-code']"));
    		String responseValue = responseCodeElement.getText();
    		System.out.println("response is : "+ responseValue);

    		if (responseValue.equals("200") && hrefValue.equals("https://reqres.in/api/users?page=2")) {
    		    System.out.println("href and Response value matches the expected value");
    		} else {
    		    throw new RuntimeException("href value does not match the expected value"); 
    		}   
                        
    	}    	
       catch (NoSuchElementException e) {
    	       	   throw new RuntimeException("Button not found."); 
        }
      }
    @When("User click GET SINGLE USERS button")
    public void user_click_get_single_users_button() {
    	try {
            WebElement buttonElement2 = driver.findElement(By.xpath("//li[@data-id='users-single']"));
            buttonElement2.click();                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    	}   
    @Then("User should see sample2 request and response")
    public void user_should_see_sample2_request_and_response() {       
       try {    		
    		WebElement spanElement = driver.findElement(By.xpath("//span[@class='url' and @data-key='url']"));
    		String url = spanElement.getText();
    		WebElement responseCodeElement = driver.findElement(By.xpath("//div[@class='response']//strong[1]"));
    		String responseValue = responseCodeElement.getText();
    		String actualResponseCode = responseValue.trim().split("\n")[1];
    		System.out.println("response is : "+ actualResponseCode);
    		System.out.println("url is : "+ url);

    		if (actualResponseCode.equals("200") && url.equals("/api/users/2")) {
    		    System.out.println("URL and Response value matches the expected value");
    		} else {
    		    throw new RuntimeException("URL OR Response value does not match the expected value"); 
    		}
                        
    	}    	
       catch (NoSuchElementException e) {
    	       	   throw new RuntimeException("Button not found."); 
        }
      }
       
    @When("User click GET SINGLE USER NOT FOUND button")
    public void user_click_get_single_user_not_found_button() {
    	try {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    		WebElement buttonElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Single user not found']")));

    		buttonElement3.click();                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    }
    @Then("User should see sample3 request and response")
    public void user_should_see_sample3_request_and_response() {       
      try {    		
    	WebElement urlElement3 = driver.findElement(By.xpath("//span[@class='url' and @data-key='url' and text()='/api/users/23']"));
  		String url3 =urlElement3.getText();
  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		   WebElement responseCodeElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='response-code bad' and @data-key='response-code']")));
  				    
  		String responseValue3 = responseCodeElement3.getText();
  		String actualResponseCode3 = responseValue3.trim();
  		if (actualResponseCode3.contains("\n")) {
            actualResponseCode3 = actualResponseCode3.split("\n")[1];
        }
        
  		System.out.println("response is : "+ actualResponseCode3);
  		System.out.println("url is : "+ url3);

  		if (actualResponseCode3.equals("404") && url3.equals("/api/users/23")) {
  		    System.out.println("URL and Response value matches the expected value");
  		} else {
  		    throw new RuntimeException("URL OR Response value does not match the expected value"); 
  		}
                        
    	}    	
       catch (NoSuchElementException e) {
    	       	   throw new RuntimeException("Button not found."); 
        }
      }
    
    @When("User click GET LIST RESOURCE button")
    public void user_click_get_list_resource_button() {   	
    	try {
            WebElement buttonElement = driver.findElement(By.xpath("//a[normalize-space()='List <resource>']"));
            buttonElement.click();                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    	}  
    @Then("User should see sample4 request and response")
    public void user_should_see_sample4_request_and_response() {       
        try { 
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); 		          	
        	WebElement urlElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='url' and @data-key='url' and text()='/api/unknown']")));
      		String url4 =urlElement4.getText();
      		WebElement responseCodeElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='response-code' and @data-key='response-code']")));
      		    
      		String responseValue4 = responseCodeElement4.getText();
      		String actualResponseCode4 = responseValue4.trim();
      		if (actualResponseCode4.contains("\n")) {
                actualResponseCode4 = actualResponseCode4.split("\n")[1];
            }
      		System.out.println("response is : "+ actualResponseCode4);
      		System.out.println("url is : "+ url4);

      		if (actualResponseCode4.equals("200") && url4.equals("/api/unknown")) {
      		    System.out.println("URL and Response value matches the expected value");
      		} else {
      		    throw new RuntimeException("URL OR Response value does not match the expected value"); 
      		}              
        	                       
    	}    	
       catch (NoSuchElementException e) {
    	       	   throw new RuntimeException("Button not found."); 
        }
      }
            
    @When("User click GET SINGLE RESOURCE button")
    public void user_click_get_single_resource_button() {
    	try {
            WebElement buttonElement5 = driver.findElement(By.xpath("//a[normalize-space()='Single <resource>']"));
            buttonElement5.click();
                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    }     
    
    @Then("User should see sample5 request and response")
    public void user_should_see_sample5_request_and_response() {       
        try {    		
        	WebElement urlElement5 = driver.findElement(By.xpath("//span[@class='url' and @data-key='url' and text()='/api/unknown/2']"));
      		String url5 =urlElement5.getText();
      		WebElement responseCodeElement5 = driver.findElement(By.xpath("//span[@class='response-code' and @data-key='response-code']"));
      		    
      		String responseValue5 = responseCodeElement5.getText();
      		String actualResponseCode5 = responseValue5.trim();
      		if (actualResponseCode5.contains("\n")) {
                actualResponseCode5 = actualResponseCode5.split("\n")[1];
            }
      		System.out.println("response is : "+ actualResponseCode5);
      		System.out.println("url is : "+ url5);

      		if (actualResponseCode5.equals("200") && url5.equals("/api/unknown/2")) {
      		    System.out.println("URL and Response value matches the expected value");
      		} else {
      		    throw new RuntimeException("URL OR Response value does not match the expected value"); 
      		}                         
    	}    	
       catch (NoSuchElementException e) {
    	    throw new RuntimeException("Button not found."); 
        }
      }

    @When("User click GET SINGLE RESOURCE NOT FOUND button")
    public void user_click_get_single_resource_not_found_button() {
    	try {
            WebElement buttonElement6 = driver.findElement(By.xpath("//a[normalize-space()='Single <resource> not found']"));
            buttonElement6.click();
                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    }
    @Then("User should see sample6 request and response")
    public void user_should_see_sample6_request_and_response() {       
       try {    	   
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
   		   WebElement urlElement6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='url' and @data-key='url' and text()='/api/unknown/23']")));
    	   String url6 =urlElement6.getText();    	   
		   WebElement responseCodeElement6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='response-code bad' and @data-key='response-code' and text()='404']")));  				    
  		   String responseValue6 = responseCodeElement6.getText();
  		   String actualResponseCode6 = responseValue6.trim();
  		if (actualResponseCode6.contains("\n")) {
            actualResponseCode6 = actualResponseCode6.split("\n")[1];
        }
        
  		System.out.println("response is : "+ actualResponseCode6);
  		System.out.println("url is : "+ url6);   
    	   
     		if (actualResponseCode6.equals("404") && url6.equals("/api/unknown/23")) {
     		    System.out.println("URL and Response value matches the expected value");
     		} else {
     		    throw new RuntimeException("URL OR Response value does not match the expected value"); 
     		}                        
    	}    	
       catch (NoSuchElementException e) {
    	       	   throw new RuntimeException("Button not found."); 
        }
      }

    @When("User click POST CREATE button")
    public void user_click_post_create_button() {
    	try {
            WebElement buttonElement7 = driver.findElement(By.xpath("//a[normalize-space()='Create']"));
            buttonElement7.click();
                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    }
    @Then("User should see sample7 request and response")
    public void user_should_see_sample7_request_and_response() {       
      try { 
    	  
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
  		   WebElement urlElement7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='url' and @data-key='url' and text()='/api/users']")));
    	   
    		String url7 =urlElement7.getText();
    		WebElement responseCodeElement7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='response-code' and @data-key='response-code' and text()='201']")));    		    
    		String responseValue7 = responseCodeElement7.getText();
    		String actualResponseCode7 = responseValue7.trim();
    		if (actualResponseCode7.contains("\n")) {
              actualResponseCode7 = actualResponseCode7.split("\n")[1];
          }
    		System.out.println("response is : "+ actualResponseCode7);
    		System.out.println("url is : "+ url7);

    		if (actualResponseCode7.equals("201") && url7.equals("/api/users")) {
    		    System.out.println("URL and Response value matches the expected value");
    		} else {
    		    throw new RuntimeException("URL OR Response value does not match the expected value"); 
    		}                       
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }
      }

    @When("User click PUT UPDATE button")
    public void user_click_put_update_button() {
    	try {
    		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    		WebElement buttonElement8 = driver.findElement(By.xpath("//a[@data-key='try-link' and @href='/api/users/2' and text()=' Update ']"));
            buttonElement8.click();
                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    }    
    @Then("User should see sample8 request and response")
    public void user_should_see_sample8_request_and_response() {
       try { 
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    	   WebElement urlElement8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='url' and @data-key='url' and text()='/api/users/2']")));
     		String url8 =urlElement8.getText();
     		WebElement responseCodeElement8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='response-code' and @data-key='response-code' and text()='200']")));
     		    
     		String responseValue8 = responseCodeElement8.getText();
     		String actualResponseCode8 = responseValue8.trim();
     		if (actualResponseCode8.contains("\n")) {
               actualResponseCode8 = actualResponseCode8.split("\n")[1];
           }
     		System.out.println("response is : "+ actualResponseCode8);
     		System.out.println("url is : "+ url8);

     		if (actualResponseCode8.equals("200") && url8.equals("/api/users/2")) {
     		    System.out.println("URL and Response value matches the expected value");
     		} else {
     		    throw new RuntimeException("URL OR Response value does not match the expected value"); 
     		}       
    	}    	
       catch (NoSuchElementException e) {
    	       	   throw new RuntimeException("Button not found."); 
        }             
       }
    @When("User click Support button")
    public void user_click_support_button() {
    	try {
    		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    		WebElement buttonElement9 = driver.findElement(By.xpath("//a[@style='text-decoration: none' and @href='#support-heading' and text()='Support ReqRes']"));
            buttonElement9.click();
                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    }
    
    @Then("User should see Support page")
    public void user_should_see_support_page() {
    	try { 
     	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
     	   WebElement urlElement10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='support-heading' and @class='t-center heading' and text()='Support']")));
      		String url8 =urlElement10.getText();   		
      		System.out.println("url is : "+ url8);      		

      		if (url8.equals("Support")) {
      		    System.out.println("SUPPORT PAGE IS OPENED");
      		} else {
      		    throw new RuntimeException("SUPPORT PAGE does not Open"); 
      		}       
     	}    	
        catch (NoSuchElementException e) {
     	       	   throw new RuntimeException("Button not found."); 
         }             
    }

    @Given("User Navigates to Support page")
    public void user_navigates_to_support_page() {    	
    	try { 
    		driver.get("https://reqres.in");
    		WebElement buttonElement14 = driver.findElement(By.xpath("//a[@style='text-decoration: none' and @href='#support-heading' and text()='Support ReqRes']"));
            buttonElement14.click();
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
      	   WebElement urlElement12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='support-heading' and @class='t-center heading' and text()='Support']")));
       		String url12 =urlElement12.getText();   		
       		System.out.println("url is : "+ url12);      		

       		if (url12.equals("Support")) {
       		    System.out.println("SUPPORT PAGE IS OPEN NOW");
       		} else {
       		    throw new RuntimeException("SUPPORT PAGE does not Open"); 
       		}       
      	}    	
         catch (NoSuchElementException e) {
      	       	   throw new RuntimeException("Button not found."); 
          }          
    	 	
    	
        }

    @When("User click Onetime payment button")
    public void user_click_onetime_payment_button() {
    	try {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    		WebElement urlElement11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio' and @id='supportOneTime' and @name='support' and @value='supportOneTime' and @checked]")));
    		String elementValue = urlElement11.getAttribute("value");  		
     		System.out.println("url is : "+ elementValue);

        		if (elementValue.equals("supportOneTime")) {
        		    System.out.println("One Time Payment option is Checked");
        		} else {
        		    throw new RuntimeException("One Time Payment option is Not Displayed"); 
        		}       
       	}    	
          catch (NoSuchElementException e) {
       	       	   throw new RuntimeException("Button not found.");
    }
    }

    @When("User click on Monthly payment button")
    public void user_click_on_monthly_payment_button() {
    	try {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    	    WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='supportRecurring' and text()='Monthly support ($5/month)']")));
    	    String labelValue = labelElement.getText();
    	    System.out.println("Label value is: " + labelValue);

    	    if (labelValue.equals("Monthly support ($5/month)")) {
    	        System.out.println("Monthly Payment option is available");
    	    } else {
    	        throw new RuntimeException("Monthly Payment option is not displayed");
    	    }
    	} catch (NoSuchElementException e) {
    	    throw new RuntimeException("Element not found.");
    	}

    }

    @Then("User should see Support ReqRes button")
    public void user_should_see_support_req_res_button() {
    	try {
    		   		
    		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    		WebElement buttonElement9 = driver.findElement(By.xpath("//a[@style='text-decoration: none' and @href='#support-heading' and text()='Support ReqRes']"));
    	    buttonElement9.click();;
                        
    	}    	
       catch (NoSuchElementException e) {
    	   throw new RuntimeException("Button not found."); 
        }  
    }        
    
    @After
    public void tearDown() {
        driver.quit();
    }
}