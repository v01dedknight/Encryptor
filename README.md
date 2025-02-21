# Encrypter v.1.0

! **Important clarification:** This program was written by me previously several years ago, and perhaps under a different nickname.

Encrypter v.1.0 is a simple encryption and decryption program using custom binary encoding. The program utilizes a graphical interface built with Tkinter and allows users to encode and decode text using custom binary rules specified in the `binary.txt` file.

## Features
- **Encryption:** Convert text to binary encoding using custom rules.
- **Decryption:** Decode binary messages back to text.
- **Copy & Paste:** Easily manage text input and output.
- **Customizable Encoding:** Modify the `binary.txt` file to change the encoding scheme.

## How It Works
The program uses a file called `binary.txt` where encoding rules are stored in the format:
```
character: binary_encoding
```
For example:
```
a: 01100001
b: 01100010
с: 00100000
```
The program also supports uppercase conversion and certain special characters.

## Getting Started
### Prerequisites
- Python 3.x
- Tkinter (usually included with Python)

### Installation
1. Clone or download the repository.
2. Make sure `binary.txt` is in the same directory as the script.

### Running the Program
```bash
python encrypter.py
```

## Usage
1. Launch the program.
2. Enter text to encrypt or binary code to decrypt.
3. Click the **Encrypt** or **Decrypt** button.
4. Use **Copy**, **Paste**, and **Clear** buttons as needed.

## Modifying the Encoding
To change the encoding scheme, edit `binary.txt` with your custom character-to-binary mappings. Ensure the format remains consistent (`character: binary_encoding`).

## License
This program is distributed under a custom license. See the license agreement displayed at program startup.

## Author
Created by me.

