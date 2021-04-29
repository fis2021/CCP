package sample.exceptions;

public class ProductAlreadyExists extends Exception{

    private String name;

        public ProductAlreadyExists(String name) {
            super(String.format(name));
            this.name = name;
        }

        public String getName() {
            return name;
        }

}
