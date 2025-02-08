package gabriel.ButtonManager;

public class SingleInstanceException extends RuntimeException
{
	SingleInstanceException(String className) {
		super("Il y a plus d'une instance de "+className);
	}
	private static final long serialVersionUID = 1L;
}