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
package com.cognifide.qa.bb.dsl.implementations;

import com.cognifide.qa.bb.dsl.exceptions.ExpectationException;
import com.cognifide.qa.bb.dsl.interfaces.Expectation;
import com.cognifide.qa.bb.dsl.interfaces.Expected;
import com.cognifide.qa.bb.dsl.interfaces.Open;
import com.cognifide.qa.bb.page.PageModel;

public class ExpectedImpl extends Condition implements Expected {

  public ExpectedImpl(PageModel pageModel) {
    super(pageModel);
  }

  @Override
  public Expected expected(Expectation expectation) {
    return expected(expectation, "There was a different value, that was expected.");
  }

  @Override
  public Expected expected(Expectation expectation, String failureMessage) {
    pageModel.addExpectation(expectation, failureMessage);
    return this;
  }

  @Override
  public Expected expectedPath(String path) {
    return expectedPath(path, "There was a different path, than " + path);
  }

  @Override
  public Expected expectedPath(String path, String failureMessage) {
    pageModel.addExpectation(webDriver -> {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(pageModel.getProtocol())
          .append("//")
          .append(pageModel.getHost())
          .append(path.startsWith("/") ? path : "/" + path);
      String expectedUrl = stringBuilder.toString();
      return webDriver.getCurrentUrl().equals(expectedUrl);
    }, failureMessage);
    return this;
  }

  @Override
  public Expected expectedTitle(String title) {
    return expectedTitle(title, "There was a different page title than expected " + title);
  }

  @Override
  public Expected expectedTitle(String title, String failureMessage) {
    pageModel.addExpectation(webDriver -> webDriver.getTitle().equals(title), failureMessage);
    return this;
  }

  @Override
  public void open() throws ExpectationException {
    Open open = new OpenImpl(pageModel);
    open.open();
  }
}
