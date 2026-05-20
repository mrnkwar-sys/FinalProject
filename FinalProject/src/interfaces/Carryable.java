package interfaces;

import mainclasses.TypeWeight;

public interface Carryable {
	TypeWeight getWeight();
	
	default String suggestCase() {
		String needsCase;
		
		if (this.getWeight() == TypeWeight.HEAVY) {
			needsCase = "You should use a case with this one";
		} else {
			needsCase = "You do not need a case for this one, but it will be cute";
		}
		
		return needsCase;
	} 
}
