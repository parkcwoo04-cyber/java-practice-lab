
# Project Name

Habit Insight Console

---

# Planning Disclaimer

This document is a preliminary project plan created before implementation.

The actual project may differ from this document as design decisions evolve throughout development.

Class structures, workflows, features, and future roadmap items may be modified, added, or removed based on implementation experience and learning outcomes.

This document serves as a guideline rather than a strict specification.

--- 
# Project Goal

Habit Insight Console is a console-based habit tracking and analytics system.

The purpose of this project is not simply to store habit records, but to design a small software system where multiple objects collaborate through object-oriented principles.

The project serves as a bridge between:

- Java Fundamentals
- Object-Oriented Programming
- Collection Framework
- Exception Handling
- Data-Oriented Thinking
- Spring Boot Backend Development
- Product/Data Analytics Portfolio Projects

The long-term goal is to gradually evolve this console application into a full-stack habit analytics platform.

---

# Core Learning Objectives

This project is designed to strengthen the following areas:

### Java Fundamentals

- Classes and Objects
- Constructors
- Instance Variables
- Methods
- Collections
- Exception Handling

### Object-Oriented Design

- Responsibility Separation
- Abstraction
- Inheritance
- Polymorphism
- Interfaces
- Encapsulation

### Software Design

- Domain Modeling
- Object Collaboration
- Layered Thinking
- Scalable Architecture

### Product/Data Thinking

- Habit Tracking
- User Behavior Analysis
- Engagement Metrics
- Retention-Oriented Design

---

# Project Concept

Users can register various habits and track their progress over time.

Each habit may belong to a different category and have different behavior depending on its type.

The system collects habit data and generates insights such as:

- Completion Rate
- Current Streak
- Best Streak
- Category Statistics
- High Performing Habits
- Underperforming Habits

The project emphasizes object collaboration and domain design rather than UI complexity.

---

# File Structure

```
HabitInsightConsole/
├── HabitInsightConsoleApp.java
│
├── Trackable.java                  // interface
│
├── Habit.java                      // abstract parent class, implements Trackable
│   ├── StudyHabit.java             // child class extends Habit
│   ├── ExerciseHabit.java          // child class extends Habit
│   └── GeneralHabit.java           // child class extends Habit
│
├── HabitCategory.java              // enum
├── HabitStatus.java                // enum
│
├── HabitTracker.java               // manages Habit objects
├── HabitStatistics.java            // analyzes Habit objects
│
└── InvalidHabitException.java      // custom exception
```

---

# Relationship Overview

```text
Trackable
   ↑
Habit (abstract)
   ↑
   ├── StudyHabit
   ├── ExerciseHabit
   └── GeneralHabit

HabitTracker
   ├── manages ArrayList<Habit>
   ├── validates habits
   ├── records progress
   └── requests analytics

HabitStatistics
   └── analyzes Habit data
```

---

# Class Design

---

## HabitInsightConsoleApp

### Role

Entry point of the application.

Responsible for:

- Creating the HabitTracker
- Creating sample habits
- Registering habits
- Simulating progress tracking
- Triggering reports

### Responsibilities

```text
Program Startup
Data Initialization
Workflow Execution
```

### Design Note

This class should contain as little business logic as possible.

Business rules, validation, and analytics should be delegated to other classes.

In a future Spring Boot version, much of its responsibility will move into Controllers and application startup configuration.

---

## Trackable

### Type

```java
public interface Trackable
```

### Role

Defines the behavior required for any object that can be tracked.

### Suggested Methods

```java
void markCompleted();

double calculateCompletionRate();

boolean isCompleted();

String getProgressSummary();
```

### Design Note

Trackable allows future expansion.

Potential future implementations:

```text
Habit
Goal
Routine
StudySession
Challenge
```

---

## Habit

### Type

```java
public abstract class Habit implements Trackable
```

### Role

The central domain object of the application.

Provides common fields and behaviors shared by all habit types.

### Fields

```java
private final String habitId;

private String title;

private HabitCategory category;

private HabitStatus status;

private int targetCount;

private int completedCount;

private int currentStreak;

private int bestStreak;

private String createdDate;
```

### Responsibilities

```text
Store habit data
Manage progress
Manage status
Track streaks
Calculate completion rate
```

### Common Methods

```java
markCompleted()

calculateCompletionRate()

pause()

activate()

archive()

getBasicInfo()

getHabitType()
```

### Design Note

Habit should remain focused on domain state and behavior.

Avoid adding analytics or system-wide logic inside this class.

Future Spring Boot mapping:

```text
Habit
↓
Habit Entity
↓
Database Table
```

---

## StudyHabit

### Type

```java
public class StudyHabit extends Habit
```

### Role

Represents learning-oriented habits.

### Example Habits

```text
Java Study
SQL Practice
LeetCode
Spring Boot Review
```

### Fields

```java
private String subject;

private int totalStudyMinutes;

private int studySessionCount;
```

### Methods

```java
addStudySession()

getAverageStudyMinutes()

getHabitType()

getProgressSummary()
```

### Design Note

This habit type is highly aligned with the user's long-term Product/Data and Engineering Growth System.

Future analytics could include:

```text
Study Time Trends
Subject Distribution
Learning Velocity
```

---

## ExerciseHabit

### Type

```java
public class ExerciseHabit extends Habit
```

### Role

Represents fitness and physical activity habits.

### Fields

```java
private String exerciseType;

private int totalExerciseMinutes;

private int workoutCount;
```

### Methods

```java
addWorkout()

getAverageWorkoutMinutes()

getHabitType()

getProgressSummary()
```

### Design Note

ExerciseHabit demonstrates specialization through inheritance.

It shares core habit behavior while introducing domain-specific metrics.

---

## GeneralHabit

### Type

```java
public class GeneralHabit extends Habit
```

### Role

Represents habits that do not require specialized behavior.

### Example Habits

```text
Drink Water
Read Books
Journal Writing
Meditation
```

### Fields

```java
private String memo;
```

### Methods

```java
updateMemo()

getHabitType()

getProgressSummary()
```

### Design Note

Not every habit requires its own subclass.

GeneralHabit acts as a safe default implementation.

---

## HabitCategory

### Type

```java
enum HabitCategory
```

### Role

Represents habit categories.

### Design Note

Using enums:

- Prevents typo-related bugs
- Simplifies statistics
- Improves database consistency

---

## HabitStatus

### Type

```java
enum HabitStatus
```

### Role

Represents the lifecycle state of a habit.

### Design Note

Useful for:

- Filtering
- Reporting
- Dashboard Generation
- Analytics

---

## HabitTracker

### Role

Core management service.

### Fields

```java
private ArrayList<Habit> habits;

private HashSet<String> habitTitles;

private ArrayList<String> rejectedHabitMessages;

private int nextHabitNumber;
```

### Responsibilities

```text
Habit Registration
Habit Deletion
Habit Search
Progress Recording
Validation
ID Generation
Collection Management
```

### Methods

```java
addHabit()

removeHabit()

findHabitById()

completeHabit()

getAllHabits()

getActiveHabits()

validateHabit()

generateHabitId()

printAllHabits()

printRejectedHabits()
```

### Design Note

HabitTracker currently combines Service and Repository responsibilities.

Future Spring Boot mapping:

```text
HabitTracker
    ↓

HabitService
    ↓

HabitRepository
```

Designing small focused methods now will significantly simplify migration later.

---

## HabitStatistics

### Role

Dedicated analytics component.

### Responsibilities

```text
Overall Completion Rate
Category Statistics
Status Statistics
Best Streak Analysis
Low Performance Detection
Summary Reports
```

### Example Methods

```java
calculateOverallCompletionRate()

countByCategory()

countByStatus()

findBestStreakHabit()

findLowestCompletionHabit()

printSummaryReport()
```

### Design Note

Analytics should be separated from HabitTracker.

This separation supports future evolution into a Product Analytics Service.

Future mapping:

```text
HabitStatistics
      ↓
HabitAnalyticsService
      ↓
Dashboard Backend
```

---

## InvalidHabitException

### Type

```java
public class InvalidHabitException extends Exception
```

### Role

Represents invalid habit data.

### Validation Examples

```text
Blank Title
Duplicate Habit
Invalid Target Count
Missing Category
Invalid State
```

### Design Note

Clear exception types improve maintainability and debugging.

Future Spring Boot mapping:

```text
InvalidHabitException
        ↓
GlobalExceptionHandler
        ↓
API Error Response
```

---

# Application Workflow

## Phase 1 — System Initialization

```text
Application Starts
        ↓
HabitTracker Created
        ↓
Sample Habits Created
        ↓
Habit Registration
```

---

## Phase 2 — Habit Registration

```text
User Input
      ↓
HabitTracker
      ↓
Validation
      ↓
Accepted or Rejected
```

Validation includes:

- Empty Title Check
- Duplicate Check
- Target Validation
- Status Validation

---

## Phase 3 — Progress Tracking

```text
User Action
      ↓
HabitTracker
      ↓
Habit
      ↓
Progress Updated
```

Updates include:

- Completion Count
- Completion Rate
- Current Streak
- Best Streak

---

## Phase 4 — Analytics Generation

```text
HabitTracker
      ↓
HabitStatistics
      ↓
Analysis
      ↓
Report Data
```

Analytics include:

- Overall Completion Rate
- Category Completion Rate
- Best Habit
- Weakest Habit
- Status Distribution

---

## Phase 5 — Reporting

```text
Generate Summary
      ↓
Display Statistics
      ↓
Display Habit Performance
      ↓
Display Insights
```

---

# Object Collaboration

## Registration Flow

```text
HabitInsightConsoleApp
          ↓
HabitTracker
          ↓
Validation
          ↓
Habit Collection
```

---

## Tracking Flow

```text
User
 ↓
HabitTracker
 ↓
Habit
 ↓
Trackable
```

Each Habit object manages its own progress.

---

## Analytics Flow

```text
HabitTracker
      ↓
HabitStatistics
      ↓
Summary Results
```

Analytics responsibilities remain isolated.

---

# Design Philosophy

This project is not a CRUD exercise.

The primary goals are:

```text
Object Responsibility
Object Collaboration
Abstraction
Extensibility
Scalable Architecture
```

Every class should have a clear reason to exist.

The design should remain flexible enough to evolve into a real application.

---

# Future Upgrade Roadmap

## Version 1 — Console OOP Version

### Goal

Practice Java object-oriented programming and domain modeling.

### Features

```text
Habit Registration
Progress Tracking
Status Management
Completion Rate Calculation
Streak Tracking
Analytics Summary
Exception Handling
```

---

## Version 2 — File Persistence

### Goal

Allow habit data to survive between program executions.

### Features

```text
Save Habits to File
Load Habits from File
Simple Data Backup
Basic File Validation
```

### Learning Focus

```text
File I/O
Data Serialization Concepts
Exception Handling
```

---

## Version 3 — Spring Boot Migration

### Goal

Transform the console application into a simple REST API.

### Architecture Transformation

```text
HabitInsightConsoleApp
          ↓
HabitController

HabitTracker
          ↓
HabitService

Habit
          ↓
Habit Entity

HabitStatistics
          ↓
HabitAnalyticsService
```

### Example APIs

```text
POST   /habits

GET    /habits

GET    /habits/{id}

PATCH  /habits/{id}/complete

PATCH  /habits/{id}/pause

DELETE /habits/{id}

GET    /habits/statistics
```

### Learning Focus

```text
Spring Boot
REST API
Controller-Service Structure
JSON
HTTP
```

---

## Version 4 — Simple Web Interface

### Goal

Provide a lightweight web interface for habit tracking.

### Features

```text
Habit List Page
Habit Detail Page
Completion Tracking
Statistics Dashboard
```

### Suggested Stack

```text
Spring Boot
Thymeleaf
Bootstrap
```

or

```text
Spring Boot
React
```

### Learning Focus

```text
Frontend / Backend Communication
API Integration
Basic UI Design
```

---

# Long-Term Vision

```text
Java OOP Project
        ↓
File-Based Application
        ↓
Spring Boot REST API
        ↓
Simple Web Application
```

The primary purpose of this project is to strengthen Java, object-oriented design, and Spring Boot fundamentals while creating a small but complete portfolio project.