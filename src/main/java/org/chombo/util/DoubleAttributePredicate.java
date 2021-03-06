/*
 * chombo: Hadoop Map Reduce utility
 * Author: Pranab Ghosh
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */


package org.chombo.util;

/**
 * @author pranab
 *
 */
public class DoubleAttributePredicate extends AttributePredicate {
	private double value;
	
	/**
	 * @param attribute
	 * @param operator
	 * @param value
	 */
	public DoubleAttributePredicate(int attribute, String operator, String value) {
		super(attribute, operator);
		this.value = Double.parseDouble(value);
	}

	@Override
	public void build(int attribute, String operator, String value) {
		this.attribute = attribute;
		this.operator = operator;
		this.value = Double.parseDouble(value);
	}
	
	@Override
	public boolean evaluate(String[] record) {
		double operand = Double.parseDouble(record[attribute]);
		return evaluateHelper(operand);
	}

	@Override
	public boolean evaluate(String field) {
		return evaluateHelper(Double.parseDouble(field));
	}
	
	/**
	 * @param operand
	 * @return
	 */
	private boolean evaluateHelper(double operand) {
		boolean status = false;
		if (operator.equals(GREATER_THAN)) {
			status = operand > value;
		} else if (operator.equals(LESS_THAN)) {
			status = operand < value;
		} else if (operator.equals(EQUAL_TO)) {
			status = operand == value;
		}  else {
			throw new IllegalArgumentException("invalid operator");
		}
		return status;
	}

	@Override
	public void build(String predicate) {
		// TODO Auto-generated method stub
		
	}

}
