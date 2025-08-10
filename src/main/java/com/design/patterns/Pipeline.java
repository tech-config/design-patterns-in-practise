package com.design.patterns;

import java.util.function.Function;
import java.util.function.Predicate;

public class Pipeline {

    public static void main(String[] args) {
        String test = "HEL";

        String result = PipelineBuilder.startsWith(DataValidator::new)
                .andThen(LowerCaseConverter::new)
                .andThen(LengthAppender::new)
                .build().apply(test);

        System.out.println(result);
    }

    static class PipelineBuilder {

        private Function<String, String> currentStage;

        public static PipelineBuilder startsWith(Function<Function<String, String>, Function<String, String>> stage) {
            PipelineBuilder pipelineBuilder = new PipelineBuilder();
            pipelineBuilder.currentStage = stage.apply(Function.identity());
            return pipelineBuilder;
        }

        public PipelineBuilder andThen(Function<Function<String, String>, Function<String, String>> stage) {
            currentStage = currentStage.andThen(stage.apply(Function.identity()));
            return this;
        }

        public Function<String, String> build() {
            return currentStage;
        }
    }

    static class DataValidator implements Function<String, String> {
        private final Predicate<String> isNotEmpty = s -> null != s && !s.trim().isEmpty();
        private final Predicate<String> isNotTooShort = s -> s.length() >= 10;

        private final Function<String, String> next;

        public DataValidator(Function<String, String> next) {
            this.next = next;
        }

        @Override
        public String apply(String s) {
            if (isNotEmpty.and(isNotTooShort).test(s)) {
                return next.apply(s);
            }
            return "Too Short";
        }
    }

    static class LowerCaseConverter implements Function<String, String> {
        private final Function<String, String> convertToLowerCase = String::toLowerCase;

        private final Function<String, String> next;

        public LowerCaseConverter(Function<String, String> next) {
            this.next = next;
        }

        @Override
        public String apply(String s) {
            return next.apply(convertToLowerCase.apply(s));
        }
    }

    static class LengthAppender implements Function<String, String> {
        private final Function<String, String> appendLength = s -> s.concat(" Size: "+ s.length());

        private final Function<String, String> next;

        public LengthAppender(Function<String, String> next) {
            this.next = next;
        }

        @Override
        public String apply(String s) {
            return next.apply(appendLength.apply(s));
        }
    }
}
