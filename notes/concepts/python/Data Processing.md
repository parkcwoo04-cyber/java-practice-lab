
Data preprocessing is the process of cleaning, organizing, and transforming data into a format suitable for analysis. It improves data quality and helps produce accurate and reliable results.

## Data Preparation and Loading

A data source is a location or system from which data is obtained. Common data sources include CSV files, Excel files, databases, and JSON files. It is important to understand the characteristics of each data source.

### Loading a CSV File

A CSV file is a file format that stores data in values separated by commas.

```python
import pandas as pd  
  
df_csv = pd.read_csv('data.csv')  
print(df_csv.head())
```

- Use pandas read_csv() function to load to load data from a CSV file.
- The head() function displays the first few rows of a DataFrame, allowing you to check the data.

### Loading an Excel File

An Excel file is well suited for storing tabular data and can contain multiple worksheets.

```python
import pandas as pd

df_excel = pd.read_excel('data.xlsx', sheet_name='Sheet1')
print(df_excel.head())
```

### Loading a JSON File

A JSON(JavaScript Object Notation) is a lightweight data format for representing structured and hierarchical data. It is widely used for exchanging data with web APIs.

```python
import json  
import pandas as pd  
  
with open('data_out.json', 'r') as json_file:  
    data_json = json.load(json_file)  
  
df_json = pd.DataFrame(data_json)  
  
print(df_json.head())
```

- Open the JSON file in read mode.
- Use json.load() to read JSON data from a file. To parse JSON data from a string, use json.loads().
- Use the pandas DataFrame() constructor to convert the resulting data into a DataFrame.

## Data Exploration

Data exploration is the process of examining a dataset to understand its structure, characteristics, and overall quality before performing detailed analysis. It typically involves checking the data types, reviewing summary statistics, identifying missing values, and looking for patterns or unusual values. This process helps analysts better understand the data and determine appropriate methods for further analysis.

### Understanding the Data Structure

The first step in data exploration is to understand the structure of the dataset. This involves checking the DataFrame's shape, the number of rows and columns, the data type of each column, and the presence of missing values. Understanding these basic characteristics helps determine the appropriate approach for subsequent data preprocessing.

```python
import panda as pd

df = pd.read_csv('data.cvs')
print(df.info())
```

- Use pandas read_csv() function to load data from a CSV file.
- Use the info() method to view a concise summary of the DataFrame. It is useful for quickly examining the structure and basic characteristics of a dataset, including:
	- the number of rows and columns
	- column names
	- the data type of each column
	- the number of non-null values in each column
	- the DataFrame's memory usage

### Statistical Summary

A statistical summary provides basic descriptive statistics that help reveal the distribution and range of the data. It is an important part of data exploration because it helps identify central tendencies, detect potential outliers, and guid subsequent analysis.

```python
import panda as pd

df = pd.read_csv('data.csv')
print(df.describe())
```

The describe() method provides the following information:
- count: the number of non-missing values in each column
- mean: the mean value
- std: the standard deviation
- min: the minimum value
- 25%: the first quartile (Q1)
- 50%: the median (Q2)
- 75%: the third quartile (Q3)
- max: the maximum value

## Data Preprocessing

### Handling Missing Values

Missing values are an important issue in data analysis because they can affect the reliability of analytical results and the performance of machine learning models. They occur when certain data values are unavailable or were not collected, and they may arise for a variety of reasons.

Handling missing values requires more than simply filling in empty entries. An appropriate strategy should be chosen based on the characteristics of the data and the reasons the values are missing. This is an important preprocessing step because it can significantly affect the reliability of the analysis. Missing values can be handled in several ways, such as removing incomplete observations or imputing values using the mean, median, or other suitable methods.

### Checking the Number and Ratio of Missing Values

```python
import pandas as pd  
  
data_dict = [  
    {'employee_id': 25001,                          'age': 20, 'department': 'Information Security'},  
    {'employee_id': 25002, 'name': 'Emily Johnson',            'department': 'Technology'},  
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22                            },  
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20                            },  
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}  
]  
  
df = pd.DataFrame(data_dict)  
  
print("Number of Missing Values for each row")  
print(df.isnull().sum())  
  
print("Ratio of Missing Values for each row")  
print(df.isnull().mean()*100)
```
```
Outcome

Number of Missing Values for each row
employee_id    0
age            1
department     2
name           1
dtype: int64

Ratio of Missing Values for each row
employee_id     0.0
age            20.0
department     40.0
name           20.0
dtype: float64
```

#### Removing the Missing Value

The simplest approach is to remove rows or columns containing missing values. However, this can result in a loss of data.

```python
import pandas as pd  
  
data_dict = [  
    {'employee_id': 25001,                          'age': 20, 'department': 'Information Security'},  
    {'employee_id': 25002, 'name': 'Emily Johnson',            'department': 'Technology'},  
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22                            },  
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20                            },  
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}  
]  
  
df = pd.DataFrame(data_dict)  
  
df_dropped_rows = df.dropna()  
print("Removing rows with missing values:")  
print(df_dropped_rows)  
  
df_dropped_cols = df.dropna(axis=1)  
print("Removing columns with missing values:")  
print(df_dropped_cols)
```
```
Outcome

Removing rows with missing values:
   employee_id   age   department           name
4        25005  23.0  Development  Daniel Wilson

Removing columns with missing values:
   employee_id
0        25001
1        25002
2        25003
3        25004
4        25005
```

##### Inputting Missing Values with the Mea, Median, or Mode

For numerical data, missing values can be imputed using the mean of the corresponding column. Mean imputation is generally suitable when the data is relatively symmetric and not heavily influenced by outliers.

Median imputation is often a better choice when the data is skewed or contains outliers because the median is less sensitive to extreme values.

For categorical data, missing values can be imputed using the mode, which replaces them with the most frequently occurring category.

```python
import pandas as pd  
  
data_dict = [  
    {'employee_id': 25001,                          'age': 20, 'department': 'Information Security'},  
    {'employee_id': 25002, 'name': 'Emily Johnson',            'department': 'Technology'},  
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22                            },  
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20                            },  
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}  
]  
  
df = pd.DataFrame(data_dict)  
  
df_mean = df.fillna({'age': df['age'].mean()})  
print(df_mean)  
  
df_median = df.fillna({'age': df['age'].median()})  
print(df_median)  
  
df_mode = df.fillna({'department': df['department'].mode()[0]})  
print(df_mode)
```
```
Outcome

   employee_id    age            department           name
0        25001  20.00  Information Security            NaN
1        25002  21.25            Technology  Emily Johnson
2        25003  22.00                   NaN  Michael Brown
3        25004  20.00                   NaN  Jessica Davis
4        25005  23.00           Development  Daniel Wilson

   employee_id   age            department           name
0        25001  20.0  Information Security            NaN
1        25002  21.0            Technology  Emily Johnson
2        25003  22.0                   NaN  Michael Brown
3        25004  20.0                   NaN  Jessica Davis
4        25005  23.0           Development  Daniel Wilson

   employee_id   age            department           name
0        25001  20.0  Information Security            NaN
1        25002   NaN            Technology  Emily Johnson
2        25003  22.0           Development  Michael Brown
3        25004  20.0           Development  Jessica Davis
4        25005  23.0           Development  Daniel Wilson
```

### Removing Duplicate Data

Duplicate records can distort statistical results and reduce the accuracy of data analysis. Therefore, identifying and removing duplicate data is an important preprocessing step.

```python
import pandas as pd

data_dict = [
    {'employee_id': 25001, 'name': 'James Smith', 'age': 20, 'department': 'Information Security'},
    {'employee_id': 25001, 'name': 'James Smith', 'age': 20, 'department': 'Information Security'},
    {'employee_id': 25002, 'name': 'Emily Johnson', 'age': 21, 'department': 'Technology'},
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22, 'department': 'Production'},
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20, 'department': 'Strategy'},
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'},
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}
]

df = pd.DataFrame(data_dict)

print("Before removing duplicates:")
print(df)

df_no_duplicates = df.drop_duplicates()

print("\nAfter removing duplicates:")
print(df_no_duplicates)
```

## Data Type Conversion

When the data type is not suitable for analysis, it may need to be converted into a more appropriate type. Fore example, date and time values stored as strings can be converted to the datetime type.

```python
import pandas as pd  
  
data_dict = [  
    {'employee_id': 25001, 'name': 'James Smith', 'age': 20, 'hire_date': '2022-03-02'},  
    {'employee_id': 25002, 'name': 'Emily Johnson', 'age': 21, 'hire_date': '2021-03-02'},  
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22, 'hire_date': '2020-03-02'},  
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20, 'hire_date': '2022-03-02'},  
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'hire_date': '2019-03-02'}  
]  
  
df = pd.DataFrame(data_dict)  
  
print("Data types before conversion:")  
print(df.dtypes)  
  
df['hire_date'] = pd.to_datetime(df['hire_date'])  
  
print("\nData types after conversion:")  
print(df.dtypes)
```

### Categorical Data Encoding

Categorical data often needs to be converted into numerical form before it can be used in data analysis or machine learning. Two common methods are one-hot encoding and label encoding. These techniques transform categorical values into numerical representations that can be processed by analytical methods and machine learning models.

One-hot encoding and label encoding are methods for converting categorical data into numerical form. One-hot encoding creates a separate binary column for each category, so it is suitable for nominal data with no natural order. Label encoding assigns an integer to each category, which is more compact but may introduce an unintended sense of order between categories. Therefore, one-hot encoding is generally preferred for unordered categorical features, while label encoding should be used carefully. In scikit-learn, `LabelEncoder` is mainly intended for target labels, while `OrdinalEncoder` is more appropriate for categorical input features.

### Data Normalization and Standardization

Normalization and standardization are techniques used to rescale numerical features so that differences in scale do not disproportionately affect data analysis or machine learning models. Min-max normalization typically scales values to a range between 0 and 1, while standardization transforms the data to have a mean of 0 and a standard deviation of 1.

```python
import pandas as pd  
  
data_dict = [  
    {'employee_id': 25001, 'name': 'James Smith', 'age': 20, 'department': 'Information Security'},  
    {'employee_id': 25002, 'name': 'Emily Johnson', 'age': 21, 'department': 'Technology'},  
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22, 'department': 'Production'},  
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20, 'department': 'Strategy'},  
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}  
]  
  
df = pd.DataFrame(data_dict)  
  
# Normalization (Min-Max Scaling)  
df['age_normalized'] = (  
    (df['age'] - df['age'].min()) /  
    (df['age'].max() - df['age'].min())  
)  
  
# Standardization (Z-score Standardization)  
df['age_standardized'] = (  
    (df['age'] - df['age'].mean()) /  
    df['age'].std()  
)  
  
print("DataFrame after normalization and standardization:")  
print(df)
```
```
Outcome

DataFrame after normalization and standardization:
   employee_id           name  ...  age_normalized age_standardized
0        25001    James Smith  ...        0.000000        -0.920358
1        25002  Emily Johnson  ...        0.333333        -0.153393
2        25003  Michael Brown  ...        0.666667         0.613572
3        25004  Jessica Davis  ...        0.000000        -0.920358
4        25005  Daniel Wilson  ...        1.000000         1.380537

[5 rows x 6 columns]
```

Min-max normalization rescales data to a fixed range while preserving the relative positions of the original values.

### Data Indexing

Data indexing is the process of selecting specific elements or subsets of data from a dataset. In pandas, data can be selected using column names, row indexes, positional indexes, or boolean conditions. This is useful for tasks such as accessing all values in a particular column or filtering rows that satisfy specific conditions.

```python
import pandas as pd

data_dict = [
    {'employee_id': 25001, 'name': 'James Smith', 'age': 20, 'department': 'Information Security'},
    {'employee_id': 25002, 'name': 'Emily Johnson', 'age': 21, 'department': 'Technology'},
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22, 'department': 'Production'},
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20, 'department': 'Strategy'},
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}
]

df = pd.DataFrame(data_dict)

print("Original DataFrame:")
print(df)

print("\nName column:")
print(df['name'])

print("\nThird row:")
print(df.loc[2])

print("\nEmployees aged 21 or older:")
print(df[df['age'] >= 21])

print("\nEmployees aged 21 or older in Information Security:")
print(df[(df['age'] >= 21) & (df['department'] == 'Information Security')])
```
```
Outcome

Original DataFrame:
   employee_id           name  age            department
0        25001    James Smith   20  Information Security
1        25002  Emily Johnson   21            Technology
2        25003  Michael Brown   22            Production
3        25004  Jessica Davis   20              Strategy
4        25005  Daniel Wilson   23           Development

Name column:
0      James Smith
1    Emily Johnson
2    Michael Brown
3    Jessica Davis
4    Daniel Wilson
Name: name, dtype: str

Third row:
employee_id            25003
name           Michael Brown
age                       22
department        Production
Name: 2, dtype: object

Employees aged 21 or older:
   employee_id           name  age   department
1        25002  Emily Johnson   21   Technology
2        25003  Michael Brown   22   Production
4        25005  Daniel Wilson   23  Development

Employees aged 21 or older in Information Security:
Empty DataFrame
Columns: [employee_id, name, age, department]
Index: []
```

### Data Slicing

Data slicing is the process of selecting a specific range of rows or columns from a dataset. It allows you to extract a subset of the data and is particularly useful when you need to analyze or process only a certain portion of a large dataset.

```python
import pandas as pd  
  
data_dict = [  
    {'employee_id': 25001, 'name': 'James Smith', 'age': 20, 'department': 'Information Security'},  
    {'employee_id': 25002, 'name': 'Emily Johnson', 'age': 21, 'department': 'Technology'},  
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22, 'department': 'Production'},  
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20, 'department': 'Strategy'},  
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}  
]  
  
df = pd.DataFrame(data_dict)  
  
print("Original DataFrame:")  
print(df)  
  
print("\nFirst two rows:")  
print(df.iloc[:, 0:2])  
  
print("\n2~4th rows:")  
print(df.iloc[1:4])  
  
print("\nRows 2-4, first two columns:")  
print(df.iloc[1:4, 0:2])  
  
print("\nEmployee ID and Department row:")  
print(df[['employee_id', 'department']])
```
```
Outcome

Original DataFrame:
   employee_id           name  age            department
0        25001    James Smith   20  Information Security
1        25002  Emily Johnson   21            Technology
2        25003  Michael Brown   22            Production
3        25004  Jessica Davis   20              Strategy
4        25005  Daniel Wilson   23           Development

First two rows:
   employee_id           name
0        25001    James Smith
1        25002  Emily Johnson
2        25003  Michael Brown
3        25004  Jessica Davis
4        25005  Daniel Wilson

2~4th rows:
   employee_id           name  age  department
1        25002  Emily Johnson   21  Technology
2        25003  Michael Brown   22  Production
3        25004  Jessica Davis   20    Strategy

Rows 2-4, first two columns:
   employee_id           name
1        25002  Emily Johnson
2        25003  Michael Brown
3        25004  Jessica Davis

Employee ID and Department row:
   employee_id            department
0        25001  Information Security
1        25002            Technology
2        25003            Production
3        25004              Strategy
4        25005           Development
```

### Data Sorting

Data sorting is the process of rearranging data according to a specified criterion. It helps organize data systemically, makes patterns easier to identify, and allows analyst to quickly find the highest or lowest values. Sorting is often used as a preliminary step in statistical analysis or time series analysis.

```python
import pandas as pd  
  
data_dict = [  
    {'employee_id': 25001, 'name': 'James Smith', 'age': 20, 'department': 'Information Security'},  
    {'employee_id': 25002, 'name': 'Emily Johnson', 'age': 21, 'department': 'Technology'},  
    {'employee_id': 25003, 'name': 'Michael Brown', 'age': 22, 'department': 'Production'},  
    {'employee_id': 25004, 'name': 'Jessica Davis', 'age': 20, 'department': 'Strategy'},  
    {'employee_id': 25005, 'name': 'Daniel Wilson', 'age': 23, 'department': 'Development'}  
]  
  
df = pd.DataFrame(data_dict)  
  
print("Original DataFrame:")  
print(df)  
  
print("\nSorted by age in ascending order:")  
print(df.sort_values('age'))  
  
print("\nSorted by age in descending order:")  
print(df.sort_values('age', ascending=False))  
  
print("\nSorted by age in descending order and department in ascending order:")  
print(df.sort_values(['age', 'department'], ascending=[False, True]))  
  
print("\nSorted by name in ascending order:")  
print(df.sort_values('name'))
```
```
Outcome

Original DataFrame:
   employee_id           name  age            department
0        25001    James Smith   20  Information Security
1        25002  Emily Johnson   21            Technology
2        25003  Michael Brown   22            Production
3        25004  Jessica Davis   20              Strategy
4        25005  Daniel Wilson   23           Development

Sorted by age in ascending order:
   employee_id           name  age            department
0        25001    James Smith   20  Information Security
3        25004  Jessica Davis   20              Strategy
1        25002  Emily Johnson   21            Technology
2        25003  Michael Brown   22            Production
4        25005  Daniel Wilson   23           Development

Sorted by age in descending order:
   employee_id           name  age            department
4        25005  Daniel Wilson   23           Development
2        25003  Michael Brown   22            Production
1        25002  Emily Johnson   21            Technology
0        25001    James Smith   20  Information Security
3        25004  Jessica Davis   20              Strategy

Sorted by age in descending order and department in ascending order:
   employee_id           name  age            department
4        25005  Daniel Wilson   23           Development
2        25003  Michael Brown   22            Production
1        25002  Emily Johnson   21            Technology
0        25001    James Smith   20  Information Security
3        25004  Jessica Davis   20              Strategy

Sorted by name in ascending order:
   employee_id           name  age            department
4        25005  Daniel Wilson   23           Development
1        25002  Emily Johnson   21            Technology
0        25001    James Smith   20  Information Security
3        25004  Jessica Davis   20              Strategy
2        25003  Michael Brown   22            Production
```

## Saving Processed Data

After data preprocessing is complete, the cleaned and transformed data can be saved for future analysis or use in other applications. In pandas, methods such as `to_csv()`, `to_excel()`, `to_json()`, and `to_sql()` are commonly used to save DataFrames in different formats.

```python
# Save as CSV
df.to_csv('data_out.csv', index=False)

# Save as Excel
df.to_excel('data_out.xlsx', index=False)

# Save as JSON
df.to_json('data_out.json', orient='records')
```

