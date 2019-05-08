package com.javarush.task.task24.task2407;

/*
В работе вам иногда будет нужно закастить класс к какому-нибудь интерфейсу (тут Sayable),
который не реализован в текущем классе
 */
public class Cat implements Pet {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    /**
     * Это - механизм адаптирования к другому интерфейсу - Sayable
     * Внутри метода toSayable создайте class CatPet, реализующий интерфейс Sayable
     * Логика метода say:
     * Если i < 1, то вывести на экран, что кот спит. Пример, "Васька спит."
     * Иначе вывести фразу: "имя_кота говорит мяу!". Пример для i=3, "Васька говорит мяяяу!"
     * <p/>
     * <b>Пример вывода:</b>
     * Мурзик спит.
     * Васька говорит мяяу!
     * Кошка говорит мяяяяяу!
     * Мышь пищит.
     * Томас говорит мяу!
     * <p/>
     * @param i количество букв 'я' в слове мяу
     * @return экземпляр класса CatPet
     */
    public Sayable toSayable(final int i) {
        class CatPet implements Sayable{

            @Override
            synchronized public String say() {
                String lol = "";
                if (i < 1){
                    lol = name + " спит.";
                    //System.out.println("Васька спит.");
                }else if (i>= 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int z = i;
                   // System.out.println(i);
                     while (z != 0){
                        z--;
                        stringBuilder.append("я");

                    }
                    String ya = stringBuilder.toString();
                    lol = name + " говорит м" + ya + "у!";
                    //System.out.println(name +" говорит мяу!");
                }
                return lol;
            }
        }



        return new CatPet();
    }
}