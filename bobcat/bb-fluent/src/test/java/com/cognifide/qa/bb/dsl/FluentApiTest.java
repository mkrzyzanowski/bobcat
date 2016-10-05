/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2016 Cognifide Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.cognifide.qa.bb.dsl;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cognifide.qa.bb.dsl.exceptions.ExpectationException;
import com.cognifide.qa.bb.dsl.interfaces.Path;
import com.cognifide.qa.bb.page.Page;

public class FluentApiTest {

  //Should be removed in future
  @BeforeClass
  public static void setUp() {
    Properties props = System.getProperties();
    props.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
  }

  @Test
  public void openPageTest() throws ExpectationException {
    Page.page()
        .protocol("http")
        .host("www.onet.pl")
        .path(Path.ROOT_PATH)
        .open();
  }

  @Test
  public void openPageWithPathTest() throws ExpectationException {
    Page.page()
        .protocol("https")
        .host("www.absa.co.za")
        .path("personal/save-invest/saving-for-retirement/explore")
        .open();
  }

  @Test
  public void openPageWithQueryTest() throws ExpectationException {
    Page.page()
        .protocol("https")
        .host("www.absa.co.za")
        .path("/search-results")
        .query("q", "absa")
        .open();
  }

  @Test
  public void openPageWithMultipleQueryTest() throws ExpectationException {
    Page.page()
        .protocol("https")
        .host("www.absa.co.za")
        .path("search-results")
        .query("q", "absa")
        .query("q", "blabla")
        .query("foo", "bar")
        .open();
  }

  @Test
  public void openPageWithExpectedConditionTest() throws ExpectationException {
    String expectedTitle = "Search Results";
    Page.page()
        .protocol("https")
        .host("www.absa.co.za")
        .path("/search-results")
        .query("q", "absa")
        .expected(webDriver -> webDriver.getTitle().equals(expectedTitle))
        .open();
  }

  @Test(expected = ExpectationException.class)
  public void openPageWithExpectedConditionAndFailTest() throws ExpectationException {
    String expectedTitle = "Wrong title";
    Page.page()
        .protocol("https")
        .host("www.absa.co.za")
        .path("/search-results")
        .query("q", "absa")
        .expected(webDriver -> webDriver.getTitle().equals(expectedTitle))
        .open();
  }
}
