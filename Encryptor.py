# coding: utf-8

import tkinter as tk
from tkinter import messagebox, ttk
from tkinter import *

def main_program():
    def load_encryption_dict():
        encryption_dict = {}
        with open('binary.txt', 'r', encoding='utf-8') as file:
            for line in file:
                char, binary_char = line.strip().split(':')
                char = char.strip()
                binary_char = binary_char.strip()
                if len(char) > 1:
                    char = char.decode('unicode_escape')
                encryption_dict[char] = binary_char
                if char.islower():
                    encryption_dict[char.upper()] = binary_char
                if char.startswith("\\"):
                    punctuation = char[1:]
                    encryption_dict[punctuation] = binary_char

                if char.isdigit():
                    encryption_dict[str(char)] = binary_char

        return encryption_dict

    def exit_pr():
        root.quit()

    def help():
        messagebox.showinfo('assistance', """Здравствуйте!

Рад представить вам программу «Encryptor» — простой и надежный инструмент для шифрования текста.

Программа по умолчанию шифрует текст в двоичном формате и сохраняет его в файле «binary.txt». Однако вы можете легко изменить кодировку символов, просто отредактировав этот файл вручную. Это значит, что, обменявшись файлом «binary.txt» с собеседником безопасным способом, вы сможете надежно защитить свою переписку от посторонних глаз.

«Encryptor» позволяет вам настраивать шифрование, предоставляя вам полный контроль над безопасностью ваших данных.

Надеюсь, программа поможет вам сохранить конфиденциальность и спокойствие в общении!

С уважением,
Создатель программы""")

    def donate():
        messagebox.showinfo('Поддержать разработчика', 'Скоро...')

    def encrypt():
        message = entry.get('1.0', tk.END).strip()
        encryption_dict = load_encryption_dict()

        binary_message = ''
        for char in message:
            if char == ' ':
                binary_message += '00100000 '
                continue

            if char in encryption_dict:
                binary_message += encryption_dict[char] + ' '
            else:
                binary_message += char
        
        output.delete('1.0', tk.END)
        output.insert(tk.END, binary_message)


    def decrypt():
        binary_message = entry.get('1.0', tk.END).strip()
        encryption_dict = load_encryption_dict()

        text_message = ''
        binary_chars = binary_message.split()
        for binary_char in binary_chars:
            if binary_char == '00100000':
                text_message += ' '
                continue
            
            for char, binary in encryption_dict.items():
                if binary == binary_char:
                    text_message += char
        
        output.delete('1.0', tk.END)
        output.insert(tk.END, text_message)

    def copy_text():
        text = output.get('1.0', tk.END).strip()
        if text:
            root.clipboard_clear()
            root.clipboard_append(text)

    def paste_text():
        text = root.clipboard_get()
        entry.insert(tk.END, text)

    def clear_text():
        entry.delete('1.0', tk.END)

    root = tk.Tk()
    root.title('Encryptor v. 1.1 release')
    root.geometry('350x360')

    mainmenu = tk.Menu()

    submenu = tk.Menu(tearoff=0)
    submenu.add_command(label='Помощь', command=help)
    submenu.add_command(label='Поддержать разработчика', command=donate)

    mainmenu.add_cascade(label='Другое', menu=submenu)


    label = tk.Label(root, text='\nБерегите свою безопасность\n', foreground='#FF00FF', font=('Arial', 10))
    label.pack()

    entry = tk.Text(root, height=5, width=30)
    entry.pack()

    encrypt_button = ttk.Button(root, text='Шифровать', command=encrypt)
    encrypt_button.pack()

    decrypt_button = ttk.Button(root, text='Расшифровать', command=decrypt)
    decrypt_button.pack()

    output = tk.Text(root, height=5, width=30)
    output.pack()

    copy_button = ttk.Button(root, text='Копировать', command=copy_text)
    copy_button.pack()

    paste_button = ttk.Button(root, text='Вставить', command=paste_text)
    paste_button.pack()

    clear_button = ttk.Button(root, text='Очистить', command=clear_text)
    clear_button.pack()

    root.config(menu=mainmenu)
    root.mainloop()

def accept_license():
    messagebox.showinfo("Принять", "Лицензионное соглашение принято.")
    root.destroy()
    main_program()

def reject_license():
    messagebox.showwarning("Отклонить", "Лицензионное соглашение отклонено.")
    root.destroy()

root = tk.Tk()

root.title("Лицензионное соглашение")
root.geometry("1200x800")

canvas = tk.Canvas(root)
canvas.pack(side="left", fill="both", expand=True)

scrollbar = tk.Scrollbar(root, command=canvas.yview)
scrollbar.pack(side="right", fill="y")

canvas.configure(yscrollcommand=scrollbar.set)

frame = tk.Frame(canvas)
canvas.create_window((0, 0), window=frame, anchor="nw")

license_text = """Лицензионное соглашение на использование программного обеспечения «Encryptor»

1. Общие положения
Программное обеспечение «Encryptor» (далее — «Программа») предоставляется пользователю бесплатно на условиях настоящего лицензионного соглашения (далее — «Соглашение»).

2. Разрешение на использование
Пользователь получает право использовать Программу бесплатно.

3. Ограничения
Пользователь соглашается:
Не использовать Программу в противоправных целях, включая, но не ограничиваясь, нарушением законодательства, взломом информационных систем, распространением вредоносного ПО или сокрытием противоправной деятельности.

4. Отказ от ответственности
Программа предоставляется «как есть» без каких-либо гарантий. Разработчик не несет ответственности за возможный ущерб, причиненный использованием или невозможностью использования Программы.

5. Принятие соглашения
Нажимая кнопку «Принять», пользователь соглашается со всеми пунктами настоящего соглашения и получает возможность использовать ее на условиях настоящего соглашения.
"""

text_widget = tk.Text(frame, wrap="word")
text_widget.insert("1.0", license_text)
text_widget.config(state="disabled")
text_widget.pack(fill="both", expand=True)

accept_button = tk.Button(root, text="Принять", command=accept_license)
accept_button.pack(side="left")

reject_button = tk.Button(root, text="Отклонить", command=reject_license)
reject_button.pack(side="right")

text_widget.config(height=50, width=130)

def update_canvas(event):
    canvas.configure(scrollregion=canvas.bbox("all"))

root.bind("<Configure>", update_canvas)

root.mainloop()
