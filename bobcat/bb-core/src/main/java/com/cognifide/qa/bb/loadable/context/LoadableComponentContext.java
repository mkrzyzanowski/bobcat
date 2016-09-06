/*
 * Copyright 2016 Cognifide Ltd..
 *
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
 */
package com.cognifide.qa.bb.loadable.context;

import com.cognifide.qa.bb.loadable.annotation.LoadableComponent;

/**
 *
 * Context of each field annotated with {@link LoadableComponent}
 */
public class LoadableComponentContext {

  private final Object subject;

  private final Class subjectClass;

  private final ConditionContext condition;

  public LoadableComponentContext(Object subject, Class subjectClass, ConditionContext loadableData) {
    this.subject = subject;
    this.subjectClass = subjectClass;
    this.condition = loadableData;
  }

  /**
   *
   * @return Instantiated field annotated with {@link LoadableComponent}.
   */
  public Object getSubject() {
    return subject;
  }

  /**
   *
   * @return Condition data provided by {@link LoadableComponent} annotation.
   */
  public ConditionContext getConditionContext() {
    return condition;
  }

  /**
   *
   * @return Subject's class
   */
  public Class getSubjectClass() {
    return subjectClass;
  }

}
