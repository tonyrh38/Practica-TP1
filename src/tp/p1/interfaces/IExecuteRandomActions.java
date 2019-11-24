package tp.p1.interfaces;

import tp.p1.game.Game;

public interface IExecuteRandomActions {
	
	static boolean canGenerateRandomOvni(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getOvni();
	}
	
	static boolean  canGenerateRandomBomb(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getFreq();	
	}
}