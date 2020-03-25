package SeleniumTest;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class SeleniumTest {

		private boolean in;
		private boolean res;
		private static WebDriver driver;
		public SeleniumTest(Boolean in,Boolean res) {
			this.in=in;
			this.res=res;
		}
		
		@Parameters
		public static Collection<Object[]> getData(){
			return Arrays.asList(Output());
		}
		@Before
		public void setUp()  {
			
			
		}

		@AfterClass
		public static void tearDown()  {
			System.out.println();
		}

		@Test
		public void testOutput() {	
			assertEquals(this.res, this.in); 
		}
		public static Object[][] Output(){
			final int TestCaseNumber = 20; //设置测试数量
			Boolean[][] objects=new Boolean[TestCaseNumber][2];
            System.setProperty("webdriver.gecko.driver", "F:\\geckodriver.exe"); //配置到火狐安装路径下的exe文件
			driver = new FirefoxDriver(); //打开火狐浏览器
			//创建工作簿
			XSSFWorkbook xssfWorkbook= null;
			try {
				//读取第一个工作表
				xssfWorkbook = new XSSFWorkbook(new FileInputStream("F:\\Selenium Lab2020.xlsx"));
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			//此处从0开始计数并进行行遍历
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			for (int row = 0; row < TestCaseNumber; row++) {
				int maxRol = sheet.getRow(row).getLastCellNum();
				System.out.println("--------第" + row + "行的数据如下--------");
				for (int rol = 0; rol < maxRol; rol++){
					System.out.print(sheet.getRow(row).getCell(rol) + "  ");
				}
				String StudentID = sheet.getRow(row).getCell(1) + "";
				String PassWord = sheet.getRow(row).getCell(2) + "";
				System.out.println();
				//读取完这条记录的信息后在网页中输入
				driver.get("http://103.120.226.190/selenium-demo/git-repo/");//跳转到定位网页
				WebElement element_name = driver.findElement(By.name("user_number")); //获取学号输入框的元素
				element_name.clear();//清空输入框里的内容
				element_name.sendKeys(StudentID); //在学号这栏输入获得到的学号
				WebElement element_pwd = driver.findElement(By.name("password")); //获取密码输入框的元素
				element_pwd.clear();
				element_pwd.sendKeys(PassWord);
				WebElement element_submit = driver.findElement(By.cssSelector(".btn.btn-primary.btn-user.btn-block")); //获取提交按钮
				element_submit.click(); //点击提交按钮

				//获取登录后网页上显示的github地址的网页元素
				String text = driver.findElement(By.cssSelector(".mb-2+.mb-2")).getText();
				System.out.println(text);
				//如果表格里的内容和网页上对应元素的内容相等，则信息一致，否则信息不一致
				if (PassWord.equals(text))	{
					System.out.println("信息一致");
					objects[row][0]= true;
					objects[row][1]=true;
				}
				else {
					System.out.println("信息不一致");
					objects[row][0]= false;
					objects[row][1]=true;
				}
                
			}
			driver.close();
			return objects;
		}

}
