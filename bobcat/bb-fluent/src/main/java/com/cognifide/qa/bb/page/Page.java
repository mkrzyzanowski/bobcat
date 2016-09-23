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
package com.cognifide.qa.bb.page;

import java.util.HashMap;
import java.util.Map;

import com.cognifide.qa.bb.dsl.expectation.Expectation;

public class Page {

  private String host = "";

  private String protocol = "";

  private String path = "";

  private StringBuilder queries = new StringBuilder();

  private Map<Expectation, String> expectations = new HashMap<>();

  public Page() {
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Map<Expectation, String> getExpectations() {
    return expectations;
  }

  public void setExpectations(Map<Expectation, String> expectations) {
    this.expectations = expectations;
  }

  public StringBuilder getQueries() {
    return queries;
  }

  public void setQueries(StringBuilder queries) {
    this.queries = queries;
  }

  //TODO - should check if key already exists and add that query
  public void addQuery(String key, String value) {
    queries.append(key)
        .append("=")
        .append(value)
        .append("&");
  }

  @Override
  public String toString() {
    String path = this.path.startsWith("/") ? this.path : "/" + this.path;
    String queries = anyQuery() ? "?" + queriesAsString() : "";
    return protocol + "://" + host + path + queries;
  }

  private boolean anyQuery() {
    return queries != null && !(queries.length() == 0);
  }

  private String queriesAsString() {
    return queries.substring(0, queries.length() - 1);
  }

  public void addExpectation(Expectation expectation, String failureMessage) {
    expectations.put(expectation, failureMessage);
  }
}
