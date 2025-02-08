package gabriel.ButtonManager;

public class noCoreButtonException extends RuntimeException
{
	noCoreButtonException() {
		super("Il n'y a pas de class CoreButton instancié");
	}
	private static final long serialVersionUID = 1L;
}