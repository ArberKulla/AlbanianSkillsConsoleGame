package interfaces;

@FunctionalInterface
public interface PromptAction {
    void execute(String text);
}
