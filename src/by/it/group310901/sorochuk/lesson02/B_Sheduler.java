package by.it.group310901.sorochuk.lesson02;

import java.util.ArrayList;
import java.util.List;

/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }
    //В методе main создается экземпляр класса B_Sheduler, создается массив событий events
// и вызывается метод calcStartTimes для расчета оптимального заполнения аудитории.
    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    Event[] sort(Event[] events) {
        Event buffer;
        for (int i = 0; i < events.length - 1; i++) {
            for (int j = 0; j < events.length - i - 1; j++) {
                if (events[j].stop > events[j + 1].stop) {
                    buffer = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = buffer;
                }
            }
        }
        return events;
    }
    //Этот метод рассчитывает оптимальное заполнение аудитории событиями в заданном периоде [from, to].
// Метод принимает массив событий events, начальное время from и конечное время to.
// Создается список result, который будет содержать оптимальные события для заполнения аудитории.
// Массив событий events сортируется по времени окончания, чтобы обеспечить оптимальное распределение.
// Затем происходит итерация по отсортированному массиву
// Каждое событие добавляется в список результатов.
    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        sort(events);

        int i = 0;
        while (events[i].start < from) {
            i++;
        }

        while ((i < events.length) && (events[i].stop <= to)) {
            int stopMoment = events[i].stop;
            result.add(events[i]);
            while ((i < events.length) && (events[i].stop <= to) && (events[i].start < stopMoment)) {
                i++;
            }
        }

        return result;          //вернем итог
    }
}