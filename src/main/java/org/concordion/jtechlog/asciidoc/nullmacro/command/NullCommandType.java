package org.concordion.jtechlog.asciidoc.nullmacro.command;

public class NullCommandType {

    public enum CommandType {

        EXECUTE("execute", new ExecuteNullCommand()),
        EXECUTE_ON_PARAGRAPH("executeOnParagraph", new ExecuteOnParagraphNullCommand()),
        SET("set", new SetNullCommand()),
        ;

        private String name;

        private NullCommand command;

        CommandType(String name, NullCommand command) {
            this.name = name;
            this.command = command;
        }

        public String getName() {
            return name;
        }

        public NullCommand getCommand() {
            return command;
        }

        public static NullCommand commandForName(String name) {
            for (CommandType commandType: values()) {
                if (name.equals(commandType.getName())) {
                    return commandType.getCommand();
                }
            }
            throw new IllegalArgumentException("Invalid command name: " + name);
        }
    }

}
