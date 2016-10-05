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

import com.cognifide.qa.bb.dsl.exceptions.NamedPageNotFound;
import com.cognifide.qa.bb.dsl.implementations.OpenImpl;
import com.cognifide.qa.bb.dsl.implementations.ProtocolImpl;
import com.cognifide.qa.bb.dsl.implementations.QueryImpl;
import com.cognifide.qa.bb.dsl.interfaces.Open;
import com.cognifide.qa.bb.dsl.interfaces.Protocol;
import com.cognifide.qa.bb.dsl.interfaces.Query;

public class Page {

  public static PageModel currentPage = null;

  public static Protocol page() {
    PageModel pageModel = new PageModel();
    registerPage(pageModel);
    return new ProtocolImpl(pageModel);
  }

  public static Query page(String protocol, String host, String path) {
    PageModel pageModel = PageModel.builder()
        .protocol(protocol)
        .host(host)
        .path(path)
        .build();
    registerPage(pageModel);
    return new QueryImpl(pageModel);
  }

  public static Open named(String named) throws NamedPageNotFound {
    return new OpenImpl(NamedFactory.getPage(named));
  }

  private static void registerPage(PageModel pageModel) {
    currentPage = pageModel;
  }
}
