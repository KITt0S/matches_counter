public class MyNumberException extends Throwable {

    @Override
    public String toString() {

        return "Ошибка! Количество квадратов должно быть целым положительным числом и" +
                " лежать в диапазоне от 1 до 100000000.";
    }
}
