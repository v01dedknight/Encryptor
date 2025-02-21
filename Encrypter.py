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
        messagebox.showinfo('assistance', """Hello!

I am pleased to introduce you to the "Encrypter" program - a simple and reliable tool for encrypting text.

The program by default encrypts text in binary format and saves it in the file "binary.txt". However, you can easily change the character encoding by simply editing this file manually. This means that by exchanging the “binary.txt” file with your interlocutor in a secure way, you can reliably protect your correspondence from prying eyes.

"Encrypter" allows you to customize your encryption, giving you complete control over the security of your data.

I hope the program will help you maintain privacy and peace of mind in your communications!

Sincerely,
Creator of the program""")

    def donate():
        messagebox.showinfo('Support the creator', 'Soon...')

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
    root.title('Encrypter v. 1.0 release')
    root.geometry('350x360')

    mainmenu = tk.Menu()

    submenu = tk.Menu(tearoff=0)
    submenu.add_command(label='Help', command=help)
    submenu.add_command(label='Support the creators', command=donate)

    mainmenu.add_cascade(label='Other', menu=submenu)


    label = tk.Label(root, text='\nTake care of your safety\n', foreground='#FF00FF', font=('Arial', 10))
    label.pack()

    entry = tk.Text(root, height=5, width=30)
    entry.pack()

    encrypt_button = ttk.Button(root, text='Crypt', command=encrypt)
    encrypt_button.pack()

    decrypt_button = ttk.Button(root, text='Decrypt', command=decrypt)
    decrypt_button.pack()

    output = tk.Text(root, height=5, width=30)
    output.pack()

    copy_button = ttk.Button(root, text='Copy', command=copy_text)
    copy_button.pack()

    paste_button = ttk.Button(root, text='Past', command=paste_text)
    paste_button.pack()

    clear_button = ttk.Button(root, text='Clear line', command=clear_text)
    clear_button.pack()

    root.config(menu=mainmenu)
    root.mainloop()

def accept_license():
    messagebox.showinfo("Accept", "License agreement accepted.")
    root.destroy()
    main_program()

def reject_license():
    messagebox.showwarning("Reject", "Deviation License Agreement.")
    root.destroy()

root = tk.Tk()

root.title("License Agreement")
root.geometry("1200x800")

canvas = tk.Canvas(root)
canvas.pack(side="left", fill="both", expand=True)

scrollbar = tk.Scrollbar(root, command=canvas.yview)
scrollbar.pack(side="right", fill="y")

canvas.configure(yscrollcommand=scrollbar.set)

frame = tk.Frame(canvas)
canvas.create_window((0, 0), window=frame, anchor="nw")

license_text = """License agreement for the use of Encrypter software"

    1. General provisions
    The "Encrypter" software (hereinafter referred to as the "Program") is provided to the user free of charge under the terms of this license agreement (hereinafter referred to as the "Agreement").

    2. Permission to use
    The User receives the right to use the Program free of charge.

    3. Restrictions
    The user agrees:
    Do not use the Program for illegal purposes, including, but not limited to, breaking the law, hacking information systems, distributing malware, or hiding illegal activities.

    4. Disclaimer
    The program is provided "as is" without any warranties. The Developer is not responsible for possible damage caused by the use or inability to use the Program.

    5. Acceptance of the agreement
    By clicking the "Accept" button, the user agrees with all clauses of this agreement and gets the opportunity to use it under the terms of this agreement.
"""

text_widget = tk.Text(frame, wrap="word")
text_widget.insert("1.0", license_text)
text_widget.config(state="disabled")
text_widget.pack(fill="both", expand=True)

accept_button = tk.Button(root, text="Accept", command=accept_license)
accept_button.pack(side="left")

reject_button = tk.Button(root, text="Reject", command=reject_license)
reject_button.pack(side="right")

text_widget.config(height=50, width=130)

def update_canvas(event):
    canvas.configure(scrollregion=canvas.bbox("all"))

root.bind("<Configure>", update_canvas)

root.mainloop()
