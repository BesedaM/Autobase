package by.epam.javatraining.beseda.webproject.entity;

import by.epam.javatraining.beseda.webproject.entity.exception.EnumElementException;

public class EnumElement {

	int number;
	String value;

	public EnumElement() {
	}

	public EnumElement(int number, String value) {
		this.number = number;
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) throws EnumElementException {
		if (number > 0) {
			this.number = number;
		} else {
			throw new EnumElementException();
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) throws EnumElementException {
		if (value != null) {
			this.value = value;
		} else {
			throw new EnumElementException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnumElement other = (EnumElement) obj;
		if (number != other.number)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
