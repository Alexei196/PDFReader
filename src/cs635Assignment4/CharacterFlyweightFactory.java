package cs635Assignment4;

import java.util.ArrayList;

public class CharacterFlyweightFactory {
	private ArrayList<CharacterFlyweight> existingFlyweightCache;
	/**Default Constructor for the Character flyweight factory that initializes the cache. 
	 */
	CharacterFlyweightFactory(){
		existingFlyweightCache = new ArrayList<CharacterFlyweight>();
	}
	/**Checks the already existing cache for flyweight representation of a given character and
	 * returns it. Otherwise, it creates,adds, then returns the character's flyweight representation 
	 * @param givenCharacter unicode character to retrieve flyweight of.
	 * @return Flyweight equivalent of the given unicode character
	 */
	public CharacterFlyweight getFlyweight(char givenCharacter){
		CharacterFlyweight potentialCharacter = new CharacterFlyweight(givenCharacter);
		if(! existingFlyweightCache.contains(potentialCharacter)) {
			existingFlyweightCache.add(potentialCharacter);
		}
		return existingFlyweightCache.get(existingFlyweightCache.indexOf(potentialCharacter));
	}
	/**Gives the amount of entries to the flyweight cache
	 * @return integer amount of entries in flyweight cache.
	 */
	public int getCacheLength() {
		return existingFlyweightCache.size();
	}
	
	//Overridden toString method that writes the flyweight cache to string
	public String toString() {
		return existingFlyweightCache.toString();
	}
}
