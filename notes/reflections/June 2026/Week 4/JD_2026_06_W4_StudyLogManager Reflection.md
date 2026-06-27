

## Metadata

Drill ID: JD_2026_06_W4_StudyLogManager

Linked code:
- [View Java Code](../java-drills/src/StudyLogManager/StudyLogMain.java)
- [View Java Code](../java-drills/src/StudyLogManager/StudyLog.java)
- [View Java Code](../java-drills/src/StudyLogManager/StudyLogService.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 47 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this program was to build a small study log management system using multiple classes. `StudyLog` represents one study record, `StudyLogService` manages a collection of study logs, and `StudyLogMain` controls the menu flow. The program uses `ArrayList`, loops, conditionals, methods, file input/output, and exception handling to add, search, delete, display, load, and save study records.

---

## What I Tried

- Created a `StudyLog` class to store one study record with `topic`, `minutes`, and `reflection`.
- Used private fields, a constructor, getters, setters, and a print method.
- Created a `StudyLogService` class to manage multiple `StudyLog` objects.
- Used an `ArrayList<StudyLog>` to store study records.
- Added methods for adding logs, deleting logs, printing all logs, calculating total study minutes, loading from a file, and saving to a file.
- Used file input/output to persist study logs in `StudyLog.txt`.
- Used `try-catch` / try-with-resources to handle file-related operations safely.
- Created a menu-based flow in `StudyLogMain` using `Scanner`, `while`, and `switch`.

---

## Mistakes

The main mistake was that `StudyLogService` and `StudyLogMain` did not consistently use the same `ArrayList`.

```java
ArrayList<StudyLog> studyLogs = new ArrayList<>();
StudyLogService service = new StudyLogService(studyLogs);

service.setStudyLogs(service.loadFromFile());

searchStudyLogByTopic(scanner, studyLogs);
service.saveToFile(studyLogs);
```

instead of letting `StudyLogService` fully own and manage the list:

```java
StudyLogService service = new StudyLogService();
service.setStudyLogs(service.loadFromFile());

service.searchStudyLogByTopic(scanner);
service.saveToFile();
```

This caused a data flow problem. After loading data from the file, the list inside `StudyLogService` could be different from the `studyLogs` list still being passed around in `StudyLogMain`. Because of that, search, delete, save, and total calculation could accidentally operate on different versions of the study log data.

A smaller logic mistake was resetting the counter inside the loop when printing search results.

```java
for (StudyLog studyLog : logs) {
    int count = 1;
    System.out.printf("%d. %s | minutes: %d | reflection: %s\n",
            count, topic, studyLog.getMinutes(), studyLog.getReflection());
}
```

instead of:

```java
int count = 1;
for (StudyLog studyLog : logs) {
    System.out.printf("%d. %s | minutes: %d | reflection: %s\n",
            count, studyLog.getTopic(), studyLog.getMinutes(), studyLog.getReflection());
    count++;
}
```

Because `count` was declared inside the loop, it became `1` again on every iteration. This made every search result print with the same number.

---

## Why the Mistake Happened

The main issue came from unclear object responsibility.

The program started with a good OOP direction: `StudyLogService` was supposed to manage study log data. However, `StudyLogMain` still kept direct control of the `ArrayList` and passed it into several methods. This weakened the separation of responsibilities.

In an object-oriented program, each object should have a clear role. `StudyLogService` should be responsible for managing study log data, while `StudyLogMain` should focus on menu flow and user interaction.

The `count` bug happened because of variable scope. Since `count` was declared inside the loop body, a new `count` variable was created during each iteration. The value could not accumulate across loop repetitions.

---

## Improved Solution

Change the design so that `StudyLogService` owns the list and `StudyLogMain` only asks the service to perform actions.

```java
public class StudyLogService {
    private ArrayList<StudyLog> studyLogs = new ArrayList<>();

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StudyLog.txt"))) {
            for (StudyLog studyLog : studyLogs) {
                writer.write(studyLog.getTopic() + ","
                        + studyLog.getMinutes() + ","
                        + studyLog.getReflection());
                writer.newLine();
            }
            System.out.println("Study logs saved successfully.");
        } catch (IOException e) {
            System.out.println("File save failed: " + e.getMessage());
        }
    }
}
```

Then `StudyLogMain` should call the service instead of directly managing the list.

```java
StudyLogService service = new StudyLogService();

service.loadFromFile();

service.addStudyLog(scanner);
service.showAllStudyLogs();
service.searchStudyLogByTopic(scanner);
service.saveToFile();
```

This improves the design because there is only one source of truth for the study log data. The list lives inside `StudyLogService`, so loading, adding, deleting, searching, calculating, and saving all use the same data.

The counter issue can be fixed by declaring `count` before the loop and increasing it inside the loop.

```java
int count = 1;

for (StudyLog studyLog : logs) {
    System.out.printf("%d. %s | minutes: %d | reflection: %s\n",
            count, studyLog.getTopic(), studyLog.getMinutes(), studyLog.getReflection());
    count++;
}
```

---

## What I Learned

I learned that using multiple classes is not only about separating files. Each class also needs a clear responsibility.

`StudyLog` should represent one study record.

`StudyLogService` should manage the collection of study records.

`StudyLogMain` should control the menu and user interaction.

I also learned that when a program uses an `ArrayList` as shared data, I need to check which object actually owns that list. If two different references or two different lists are used by mistake, the program may compile but still behave incorrectly.

For debugging, I should trace the data flow after loading from a file:

1. Which list receives the loaded data?
2. Which list is used for search?
3. Which list is used for delete?
4. Which list is used for save?

I also learned that loop variables must be declared in the correct scope. If a counter should increase across iterations, it should be declared before the loop, not inside the loop.

---

## Related Java Concepts

 - [[9. Class part 2|Class]]
 - [[11. Collection Framework#ArrayList|ArrayList]]
 - [[5. Loop Statement|Loop Statement]]
 - [[13. Input, Output and Files#BufferedReader|BufferedReader and BufferedWriter]]
