package sub;

/**
 * Simple object representing 1 of the stat-modifiers on an object.
 * 
 * @author michiel
 *
 */
public class StatApply {

	/**
	 * String[] STAT is ordered in a way to reflect their number 
	 * in oedit, so the number you have to choose in here is the 
	 * same as in oedit. 
	 */
	public static final int NONE = 0,				STR = 1,
							DEX = 2,				PER = 3,
							PIE = 4,				CON = 5,
							AGI = 6,				ESS = 7,
							DIS = 8,				MANA = 9,
							HP = 10,				END = 11,
							HIT = 12,				DAM = 13,
							DODGE = 14,				PARRY = 15,
							SHIELDBLOCK = 16,		CRIT = 17,
							SPELLCRIT = 18,			SPELLDAM = 19,
							UNUSED = 20,			//UNUSED = 21
							HEALING = 22,			RESIST_POISON = 23,
							RESIST_ELEMENT = 24,	RESIST_ARCANE = 25,
							RESIST_DIVINE = 26,		RESIST_NATURE = 27,
							ALL_STATS = 28,			ARMOR = 29;
	public int fApply;
	public int fModifier;
	
	public StatApply (int pApply, int pAmount){
		this.fApply = pApply;
		this.fModifier = pAmount;
	}
	
	public String toInputSubstring(){
		return ""+this.fApply+";"+this.fModifier;
	}
	
	public boolean equals(StatApply other){
		return (this.fApply == other.fApply && this.fModifier == other.fApply);
	}
}
