package tp.practica1GameObjects;


public interface IAttack {
	default boolean performAttack(GameObject other) {return false;};

	default boolean receiveLaserAttack(int damage) {return false;};
	default boolean receiveBombAttack(int damage) {return false;};
	default boolean receiveShockWaveAttack(int damage) {return false;};
}
