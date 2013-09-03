package by.bsuir.iit.abramov.game.ball.Util;

public enum ToolButtons {
	NEW("New", true), START("Start", true), RESET("Reset", true), STOP("Stop", true), PLUS(
			"+", true), MINUS("-", true), Right("->", false), Left("<-", false);
	private final String	name;
	private boolean			enable;

	ToolButtons(final String name, final boolean enable) {

		this.name = name;
		this.enable = enable;
	}

	public final String getName() {

		return name;
	}

	public final boolean isEnabled() {

		return enable;
	}
}
