# 2D list used to print the menus
menu = [["Strawberry", "Orange", "Apple", "Banana", "Watermelon"],  # Fruits
        ["Spinach", "Potato", "Green Bean", "Carrot", "Artichoke"],  # Veggies
        ["Chicken", "Beef", "Pork", "Lamb", "Salmon"],  # Meats
        ["Yogurt", "Milk", "Cheddar Cheese", "Butter", "Cottage Cheese"]]  # Dairy

# 2D list containing the nutrition facts for each food (divided by food group)
nutrition_facts = [[
    "STRAWBERRIES\n" +
    "Amount per serving: 12 g\n" +
    "Calories: 4\n" +
    "Cholestrol = 0 mg\n" +
    "Potassium = 18 mg\n" +
    "Total Carbs = 0.9 g\n" +
    "Protein = 0.1 g\n" +
    "Vitamin C = 11%\n",
    "ORANGES\n" +
    "Amount per serving: 96 g\n" +
    "Calories: 45\n" +
    "Cholestrol = 0 mg\n" +
    "Potassium = 174 mg\n" +
    "Total Carbs = 11 g\n" +
    "Protein = 0.9 g\n" +
    "Vitamin C = 85%\n",
    "APPLES\n" +
    "Amount per serving: 182 g\n" +
    "Calories: 95\n" +
    "Cholestrol = 0 mg\n" +
    "Potassium = 195 mg\n" +
    "Total Carbs = 25 g\n" +
    "Protein = 0.5 g\n" +
    "Vitamin C = 14%\n",
    "BANANAS\n" +
    "Amount per serving: 118 g\n" +
    "Calories: 105\n" +
    "Cholestrol = 0 mg\n" +
    "Potassium = 422 mg\n" +
    "Total Carbs = 27 g\n" +
    "Protein = 1.3 g\n" +
    "Vitamin C = 17%\n" +
    "Vitamin B6 = 20%\n",
    "WATERMELONS\n" +
    "Amount per serving: 280 g\n" +
    "Calories: 85\n" +
    "Cholestrol = 0 mg\n" +
    "Potassium = 314 mg\n" +
    "Total Carbs = 21 g\n" +
    "Protein = 1.7 g\n" +
    "Vitamin C = 37%\n"
],  # fruits
    [
        "SPINACH\n" +
        "Amount per serving: 1 cup\n" +
        "Calories: 7 \n" +
        "Cholestrol = 0 mg\n" +
        "Potassium = 167 mg\n" +
        "Total Carbs = 1.1 g\n" +
        "Protein = 0.9 g\n" +
        "Vitamin A = 56%\n",
        "POTATO\n" +
        "Amount per serving: 148 g\n" +
        "Calories: 110 \n" +
        "Cholestrol = 0 mg\n" +
        "Potassium = 620 mg\n" +
        "Total Carbs = 26 g\n" +
        "Protein = 3 g\n" +
        "Vitamin C = 30%\n",
        "GREEN BEAN\n" +
        "Amount per serving: 100 g\n" +
        "Calories: 31 \n" +
        "Cholestrol = 0 mg\n" +
        "Potassium = 209 mg\n" +
        "Total Carbs = 7 g\n" +
        "Protein = 1.8 g\n" +
        "Vitamin C = 27%\n",
        "CARROT\n" +
        "Amount per serving: 61 g\n" +
        "Calories: 25 \n" +
        "Cholestrol = 0 mg\n" +
        "Potassium = 195 mg\n" +
        "Total Carbs = 6 g\n" +
        "Protein = 0.6 g\n" +
        "Vitamin C = 6%\n",
        "ARTICHOKE\n" +
        "Amount per serving: 128 g\n" +
        "Calories: 60 \n" +
        "Cholestrol = 0 mg\n" +
        "Potassium = 474 mg\n" +
        "Total Carbs = 13 g\n" +
        "Protein = 4.2 g\n" +
        "Vitamin C = 25%\n",
    ],  # veggies
    [
        "CHICKEN\n" +
        "Amount per serving: 140 g\n" +
        "Calories: 335 \n" +
        "Cholestrol = 123 mg\n" +
        "Potassium = 312 mg\n" +
        "Total Carbs = 0 g\n" +
        "Protein = 38 g\n" +
        "Vitamin B6 = 30%\n",
        "BEEF\n" +
        "Amount per serving: 85 g\n" +
        "Calories: 213 \n" +
        "Cholestrol = 77 mg\n" +
        "Potassium = 270 mg\n" +
        "Total Carbs = 0 g\n" +
        "Protein = 22 g\n" +
        "Cobalamin = 36%\n",
        "PORK\n" +
        "Amount per serving: 85 g\n" +
        "Calories: 206 \n" +
        "Cholestrol = 68 mg\n" +
        "Potassium = 360 mg\n" +
        "Total Carbs = 0 g\n" +
        "Protein = 23 g\n" +
        "Vitamin B6 = 20%\n",
        "LAMB\n" +
        "Amount per serving: 85 g\n" +
        "Calories: 250 \n" +
        "Cholestrol = 82 mg\n" +
        "Potassium = 264 mg\n" +
        "Total Carbs = 0 g\n" +
        "Protein = 21 g\n" +
        "Cobalamin = 36%\n",
        "SALMON\n" +
        "Amount per serving: 198 g\n" +
        "Calories: 412 \n" +
        "Cholestrol = 109 mg\n" +
        "Potassium = 719 mg\n" +
        "Total Carbs = 0 g\n" +
        "Protein = 40 g\n" +
        "Cobalamin = 106%\n" +
        "Vitamin B6 = 65%\n",
    ],  # meats
    [
        "YOGURT\n" +
        "Amount per serving: 170 g\n" +
        "Calories: 100 \n" +
        "Cholestrol = 9 mg\n" +
        "Potassium = 240 mg\n" +
        "Total Carbs = 6 g\n" +
        "Protein = 17 g\n" +
        "Cobalamin = 21%\n",
        "MILK\n" +
        "Amount per serving: 244 g\n" +
        "Calories: 103 \n" +
        "Cholestrol = 12 mg\n" +
        "Potassium = 366 mg\n" +
        "Total Carbs = 12 g\n" +
        "Protein = 8 g\n" +
        "Calcium = 30%\n",
        "CHEDDAR CHEESE\n" +
        "Amount per serving: 28 g\n" +
        "Calories: 113 \n" +
        "Cholestrol = 29 mg\n" +
        "Potassium = 27 mg\n" +
        "Total Carbs = 0.4 g\n" +
        "Protein = 7 g\n" +
        "Calcium = 20%\n",
        "BUTTER\n" +
        "Amount per serving: 14.2 g\n" +
        "Calories: 102 \n" +
        "Cholestrol = 31 mg\n" +
        "Potassium = 3 mg\n" +
        "Total Carbs = 0 g\n" +
        "Protein = 0.1 g\n" +
        "Vitamin D = 20%\n",
        "COTTAGE CHEESE\n" +
        "Amount per serving: 225 g\n" +
        "Calories: 222 \n" +
        "Cholestrol = 38 mg\n" +
        "Potassium = 234 mg\n" +
        "Total Carbs = 8 g\n" +
        "Protein = 25 g\n" +
        "Calcium = 18%\n",
    ]  # dairy
]


# Prints the food group menu and finds out the food type
def get_type():
    food_type = 0
    print("FOOD GROUPS")
    print("1. Fruits")
    print("2. Veggies")
    print("3. Meats")
    print("4. Dairy\n")
    while food_type < 1 or food_type > 4:
        food_type = int(input("What food group would you like? (Enter the corresponding number): "))
        print()
    return food_type


# Prints fruit menu
def print_fruit_menu():
    print("FRUITS")
    for j, item in enumerate(menu[0]):
        print(f"{j + 1}. {item}")


# Prints veggie menu
def print_veggie_menu():
    print("VEGGIES")
    for j, item in enumerate(menu[1]):
        print(f"{j + 1}. {item}")


# Prints meat menu
def print_meat_menu():
    print("MEATS")
    for j, item in enumerate(menu[2]):
        print(f"{j + 1}. {item}")


# Prints dairy menu
def print_dairy_menu():
    print("DAIRY")
    for j, item in enumerate(menu[3]):
        print(f"{j + 1}. {item}")


# Prints nutrition facts
def print_nutrition_facts(type, food):
    print(nutrition_facts[type][food])


# Gets the specific food and enters the 2D indices to print_nutrition_facts
def choose_food(index):
    food = 0
    if index == 1:
        print_fruit_menu()
    elif index == 2:
        print_veggie_menu()
    elif index == 3:
        print_meat_menu()
    else:
        print_dairy_menu()
    print()
    while food < 1 or food > 5:
        food = int(input("About which food would you like to learn nutrition facts? (Enter the corresponding number): "))
        print()
    print_nutrition_facts(index - 1, food - 1)


# MAIN PROGRAM
ans = 'Y'

while ans.lower() == 'y':
    menu_index = get_type()  # Choose food type
    choose_food(menu_index)  # Find information about a specific food from the food group
    ans = input("Are there any other nutrition facts that you would like to view? (Y/N): ")
    print()

print("\nThank you for using the Nutrition Menu! Have a healthy day!")
