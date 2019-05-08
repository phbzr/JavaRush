package com.javarush.task.task23.task2304;


import java.util.List;
import java.util.Map;

/*
Inner 3
*/
public class Solution {

    private List<Task> tasks;
    private List<String> names;


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    private DbDataProvider taskDataProvider = new TaskDataProvider();
    private DbDataProvider nameDataProvider = new NameDataProvider();

    public void refresh() {
        Map taskCriteria = MockView.getFakeTaskCriteria();
        taskDataProvider.refreshAllData(taskCriteria);

        Map nameCriteria = MockView.getFakeNameCriteria();
        nameDataProvider.refreshAllData(nameCriteria);
    }

    private interface DbDataProvider<T> {
        void refreshAllData(Map criteria);
    }

    private class TaskDataProvider implements DbDataProvider<Task>{

        @Override
        public void refreshAllData(Map criteria) {

            List<Task> tasker = MockDB.getFakeTasks(criteria);
            setTasks(tasker);
        }
    }

    private class NameDataProvider implements DbDataProvider<String>{

        @Override
        public void refreshAllData(Map criteria) {

            List<String>namer = MockDB.getFakeNames(criteria);
            setNames(namer);

        }
    }

    class Task {
    }

    public static void main(String[] args) {

    }
}
