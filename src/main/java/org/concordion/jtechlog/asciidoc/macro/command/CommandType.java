package org.concordion.jtechlog.asciidoc.macro.command;

public enum CommandType {

    ASSERT_EQUALS("assertEquals", new AssertEqualsCommand()),
    ASSERT_EQUALS_ON_ALL_ROWS("assertEqualsOnAllRows", new AssertEqualsOnAllRowsCommand()),
    EXECUTE("execute", new ExecuteCommand()),
    EXECUTE_ON_PARAGRAPH("executeOnParagraph", new ExecuteOnParagraphCommand()),
    EXECUTE_ON_TABLE("executeOnTable", new ExecuteOnTableCommand()),
    EXECUTE_ON_LIST("executeOnList", new ExecuteOnListCommand()),
    SET_COMMAND("set", new SetCommand()),
    SET_ON_ALL_ROWS("setOnAllRows", new SetOnAllRowsCommand()),
    VERIFY_ROWS("verifyRows", new VerifyRowsCommand())
    ;

    private String name;

    private Command command;

    CommandType(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public Command getCommand() {
        return command;
    }

    public static Command commandForName(String name) {
        for (CommandType commandType: values()) {
            if (name.equals(commandType.getName())) {
                return commandType.getCommand();
            }
        }
        throw new IllegalArgumentException("Invalid command name: " + name);
    }
}
