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
package com.cognifide.qa.bb.loadable.mapper;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.runtime.java.guice.ScenarioScoped;

public class TestObjectTypeListener implements TypeListener {

  @Override
  public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
    Class<? super I> rawType = type.getRawType();
    if (isApplicable(rawType)) {
      encounter.register(new TestClassInjectionListener(encounter));
    }
  }

  private <I> boolean isApplicable(Class<? super I> rawType) {
    boolean result;
    if (rawType.isAnnotationPresent(RunWith.class) && !rawType.isAnnotationPresent(CucumberOptions.class)) {
      result = true;
    } else {
      result = rawType.isAnnotationPresent(ScenarioScoped.class);
    }
    return result;
  }
}
