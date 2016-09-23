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
import com.cognifide.qa.bb.dsl.interfaces.Path;
import com.cognifide.qa.bb.dsl.interfaces.PathQueryExpected;
import com.cognifide.qa.bb.dsl.interfaces.Query;
import com.cognifide.qa.bb.page.Page;

public class PathQueryExpectedImpl implements PathQueryExpected {

  private Page page;

  public PathQueryExpectedImpl(Page page) {
    this.page = page;
  }

  @Override
  public Expected expected(Expectation expectation) {
    Expected expected = new ExpectedImpl(page);
    return expected.expected(expectation);
  }

  @Override
  public Expected expected(Expectation expectation, String failureMessage) {
    Expected expected = new ExpectedImpl(page);
    return expected.expected(expectation, failureMessage);
  }

  @Override
  public Query path(String path) {
    Path pathObj = new PathImpl(page);
    return pathObj.path(path);
  }

  @Override
  public Query query(String key, String value) {
    Query query = new QueryImpl(page);
    return query.query(key, value);
  }

  @Override
  public void open() throws ExpectationException {
    Open open = new OpenImpl(page);
    open.open();
  }
}
