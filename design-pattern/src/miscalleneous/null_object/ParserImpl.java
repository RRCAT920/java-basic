package miscalleneous.null_object;

/**
 * @author huzihao
 * @since 2020/11/5 11:54
 */
public enum ParserImpl implements Parser {
    PARSER;

    private static final Action DO_NOTHING = new Action() {
        @Override
        public void doSomething() {
        }
    };

    @Override
    public Action findAction(String input) {
        return switch (input) {
            case "hi" -> () -> System.out.println("hi there");
            case "hello" -> () -> System.out.println("hello Tom");
            default -> DO_NOTHING;
        };
    }
}
