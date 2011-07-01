import java.util.ArrayList;

import sub.StatApply;

/**
 * A representation of an 'Object' in sharune, which can eventually be 
 * used to output a full command string to create this object in OLC.
 * 
 * @author Milean
 *
 */
public class OLCObject {

	//A list of wear flags with their appropriate number for oedit, and 
	//an accompanying list of EP multipliers for those wear locations.
	public final static int TAKE = 1,			FINGER = 2, 
							NECK = 3, 			BODY = 4,	
							HEAD = 5,			LEGS = 6,	
							FEET = 7,			HANDS = 8,	
							ARM = 9,			SHIELD = 10,	
							ABOUT = 11,			WAIST = 12,	
							WRIST = 13,			WIELD1 = 14,	
							HOLD1 = 15,			BACK = 16,	
							BELT = 17,			WIELD2 = 18,	
							HOLD2 = 19,			UNDER_FEET = 20,	
							EAR = 21,			FACE = 22,	
							ACROSS_BACK = 23,	EYES = 24,	
							SHOULDERS = 25,		NONE = 26,	
							LIGHT = 27;
	public final static double[] fEP_Multiplier = {0,0,1,1,3,2,2,2,2,2,2,1,1,1,2,2,1,0.5,3,3,0,1,0,1,0,2,0,1};

	private int fVnum;
	private int fLevel;
	private boolean fRare;
	private ArrayList<StatApply> fApplies;
	private int[] fWearFlags;
	
	/**
	 * Generic constructor for objects. Every field can be set with seperate
	 * Methods, only the vnum has to be set here, because just like any object 
	 * in Sharune, it's not possible to change the vnum later on.
	 * 
	 * @param pNumber The VNUM of this Object.
	 */
	public OLCObject(int pNumber){
		this.fVnum = pNumber;
		this.fApplies = new ArrayList<StatApply>();
		this.fWearFlags = new int[] {OLCObject.TAKE, OLCObject.ABOUT};
	}
	
	/**
	 * Calculate the amount of EP this object is allowed to have, in the same
	 * way that Sharune calculates it.
	 *  
	 * @return The maximum amount of EP this object is allowed to have.
	 */
	public int getMaxEP(){
		double lMultiplier = 3;
		for(int i = 0; i<fWearFlags.length; i++){
			if (OLCObject.fEP_Multiplier[this.fWearFlags[i]] > 0 && OLCObject.fEP_Multiplier[this.fWearFlags[i]] < lMultiplier){
				lMultiplier = OLCObject.fEP_Multiplier[this.fWearFlags[i]];
			}
		}
		int lResult = (int) (fLevel * lMultiplier);
		if (this.fRare){
			lResult = (int) (lResult * 1.5);
		}
		return lResult;
	}
	
	/**
	 * Add an apply of a single stat to the object. Doesn't take the max EP into account.
	 * 
	 * TODO: Extend this to add the StatApply modifier to an already existing StatApply
	 * modifier, if a StatApply with the same Stat is already in the Applies list.
	 *  
	 * @param pApply The StatApply that is added to this object.
	 */
	public void addApply(StatApply pApply){
		fApplies.add(pApply);
	}
	
	/**
	 * Remove an apply of a single stat from the list. Every apply with 
	 * the same Stat and the same Modifier is regarded as equal.
	 * 
	 * TODO: Extend this to reduce an existing StatApply by the Modifier
	 * in pApply, when a StatApply with the same Stat is found.
	 * 
	 * @param pApply The StatApply that will be removed from this object.
	 * @return Whether the object was indeed found and removed.
	 */
	public boolean removeApply(StatApply pApply){
		return fApplies.remove(pApply);
	}
	
	/**
	 * Generates a command String that can be used in OLC, to create this 
	 * object with all fields applied.
	 * 
	 * TODO: Make separate methods for editing this object later on, especially
	 * taking flags into account that are toggled, not set.
	 *   
	 * @return
	 */
	public String toCreationString(){
		//Generic start
		String lReturn = "oedit "+fVnum+";";
		
		//Wear flags
		if (fWearFlags.length > 0){
			lReturn += "8;";
			for (int lFlag : fWearFlags){
				lReturn += lFlag + ";";
			}
			lReturn += "0;";
		}
		
		//Extra flags (only RARE for now)
		if (fRare){
			lReturn += "k;10;0";
		}
		
		//Applies
		if (fApplies.size() > 0){
			lReturn += "D;";
			for (int i = 0; i < fApplies.size(); i++){
				lReturn += (i+1)+";";
				lReturn += fApplies.get(i).toInputSubstring();
			}
			lReturn += "0;";
		}
		
		//Generic quitting and saving.
		lReturn = "q;y;";
		
		return lReturn;
	}
	
}
