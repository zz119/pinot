/**
 * Copyright (C) 2014-2015 LinkedIn Corp. (pinot-core@linkedin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedin.pinot.core.common.predicate;

import java.util.Arrays;
import java.util.List;

import com.linkedin.pinot.core.common.Predicate;


public class RangePredicate extends Predicate {

  private final boolean incLower;
  private final boolean incUpper;
  private final String lowerBoundary;
  private final String upperBoundary;

  public RangePredicate(String lhs, List<String> rhs) {
    super(lhs, Type.RANGE, rhs);

    final String rangeString = rhs.get(0).trim();
    lowerBoundary = rangeString.split("\t\t")[0].substring(1, rangeString.split("\t\t")[0].length());
    upperBoundary = rangeString.split("\t\t")[1].substring(0, rangeString.split("\t\t")[1].length() - 1);

    if (rangeString.trim().startsWith("(")) {
      if (lowerBoundary.equals("*")) {
        incLower = true;
      } else {
        incLower = false;
      }
    } else {
      incLower = true;
    }

    if (rangeString.trim().endsWith(")")) {
      if (upperBoundary.equals("*")) {
        incUpper = true;
      } else {
        incUpper = false;
      }
    } else {
      incUpper = true;
    }
  }

  @Override
  public String toString() {
    return "Predicate: type: " + getType() + ", left : " + getLhs() + ", right : "
        + Arrays.toString(getRhs().toArray(new String[0])) + "\n";
  }

  public boolean includeLowerBoundary() {
    return incLower;
  }

  public boolean includeUpperBoundary() {
    return incUpper;
  }

  public String getLowerBoundary() {
    return lowerBoundary;
  }

  public String getUpperBoundary() {
    return upperBoundary;
  }

}
