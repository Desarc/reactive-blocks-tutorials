package no.ntnu.item.tutorials.exercise4b_completed.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public int number;

	public int generateNumber() {
		int number = (int)(Math.random()*8+1);
		System.out.println("Number to guess: "+number);
		return number;
	}
	
	public boolean greaterThan1(int number) {
		if (number > 1) return true;
		else return false;
	}
	
	public boolean greaterThan2(int number) {
		if (number > 2) return true;
		else return false;
	}
	
	public boolean greaterThan3(int number) {
		if (number > 3) return true;
		else return false;
	}
	
	public boolean greaterThan4(int number) {
		if (number > 4) return true;
		else return false;
	}
	
	public boolean greaterThan5(int number) {
		if (number > 5) return true;
		else return false;
	}
	
	public boolean greaterThan6(int number) {
		if (number > 6) return true;
		else return false;
	}
	
	public boolean greaterThan7(int number) {
		if (number > 7) return true;
		else return false;
	}
	
	public void numberIs1() {
		System.out.println("The number is 1!");
	}
	
	public void numberIs2() {
		System.out.println("The number is 2!");
	}
	
	public void numberIs3() {
		System.out.println("The number is 3!");
	}
	
	public void numberIs4() {
		System.out.println("The number is 4!");
	}
	
	public void numberIs5() {
		System.out.println("The number is 5!");
	}
	
	public void numberIs6() {
		System.out.println("The number is 6!");
	}
	
	public void numberIs7() {
		System.out.println("The number is 7!");
	}
	
	public void numberIs8() {
		System.out.println("The number is 8!");
	}
	
	
}