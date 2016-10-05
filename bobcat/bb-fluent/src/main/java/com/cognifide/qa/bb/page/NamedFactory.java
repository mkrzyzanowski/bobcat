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

import com.cognifide.qa.bb.dsl.exceptions.NamedPageNotFound;

public class NamedFactory {
  private static Map<String, PageModel> namedMap = new HashMap<>();

  public static void put(String name, PageModel pageModel) {
    namedMap.put(name, pageModel);
  }

  static {
    PageModel absaSearch = PageModel.builder()
        .protocol("https")
        .host("www.absa.co.za")
        .path("search-results")
        .query("q", "absa")
        .expectation(webDriver -> webDriver.getTitle().equals("Search Results"),
            "There was a different title")
        .build();

    PageModel onet = PageModel.builder()
        .protocol("http")
        .host("www.onet.pl")
        .build();

    namedMap.put("absa-search", absaSearch);
    namedMap.put("onet", onet);
  }

  public static PageModel getPage(String named) throws NamedPageNotFound {
    PageModel pageModel = namedMap.get(named);
    if (pageModel == null) {
      throw new NamedPageNotFound();
    }
    return pageModel;
  }
}
