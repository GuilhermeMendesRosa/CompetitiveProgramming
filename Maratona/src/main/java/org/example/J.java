package org.example;

import java.util.*;

public class J {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer dirQuantity = Integer.valueOf(scanner.next());
        Integer fileQuantity = Integer.valueOf(scanner.next());

        Map<Integer, Directory> mapDirectories = new HashMap<Integer, Directory>();

        Directory rootDirectory = new Directory(1);
        mapDirectories.put(1, rootDirectory);

        // create directories
        for (int i = 0; i < dirQuantity; i++) {
            int id = i + 1;
            Directory directory = new Directory(id);
            mapDirectories.put(id, directory);
        }

        // bind fathers
        for (int i = 0; i < dirQuantity - 1; i++) {
            Integer directoryId = i + 2;
            Directory directory = mapDirectories.get(directoryId);

            Integer fatherId = Integer.valueOf(scanner.next());
            Directory father = mapDirectories.get(fatherId);

            directory.bindFather(father);
        }

        // add files
        for (int i = 0; i < fileQuantity; i++) {
            Integer directoryId = Integer.valueOf(scanner.next());
            Directory directory = mapDirectories.get(directoryId);
            directory.addFile();
        }

        // define tests
        Integer testQuantity = Integer.valueOf(scanner.next());

        List<Integer> tests = new ArrayList<>();
        for (int i = 0; i < testQuantity; i++) {
            Integer directoryId = Integer.valueOf(scanner.next());
            Directory directoryToClean = mapDirectories.get(directoryId);
            Integer deletedFiles = directoryToClean.clear();

            tests.add(deletedFiles);
        }

        for (Integer test : tests) {
            System.out.println(test);
        }
    }

    static class Directory {

        Integer id;
        Integer directoryFiles = 0;
        Directory father;
        List<Directory> children = new ArrayList<>();

        public Directory(Integer id) {
            this.id = id;
        }

        public int clear() {
            int deleted = 0;

            deleted += directoryFiles;
            this.directoryFiles = 0;

            for (Directory directory : children) {
                deleted += directory.clear();
            }

            return deleted;
        }

        public void addChild(Directory directory) {
            this.children.add(directory);
        }

        public void addFile() {
            this.directoryFiles++;
        }

        public void bindFather(Directory father) {
            this.father = father;
            father.addChild(this);
        }

    }

}