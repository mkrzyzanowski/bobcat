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

import com.cognifide.qa.bb.dsl.expectation.Expectation;
import com.cognifide.qa.bb.dsl.expectation.ExpectationException;
import com.cognifide.qa.bb.dsl.interfaces.Expected;
import com.cognifide.qa.bb.dsl.interfaces.Open;
import com.cognifide.qa.bb.page.Page;

public class ExpectedImpl extends Condition implements Expected {

  public ExpectedImpl(Page page) {
    super(page);
  }

  @Override
  public Expected expected(Expectation expectation) {
    return expected(expectation, "There was a different value, that was expected.");
  }

  @Override
  public Expected expected(Expectation expectation, String failureMessage) {
    page.addExpectation(expectation, failureMessage);
    return this;
  }

  @Override
  public void open() throws ExpectationException {
    Open open = new OpenImpl(page);
    open.open();
  }
}
