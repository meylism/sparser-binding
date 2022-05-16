from __future__ import with_statement
import os


if __name__ == "__main__":
    code_dir = "/Users/meylismatiyev/Documents/sparser"
    os.chdir(f"{code_dir}/sparser-jni")
#     os.system(f"cd {code_dir}/sparser-jni")
    os.system("make clean && make")
    os.system(f"sudo ln -sfn {code_dir}/sparser-jni/libs/libsparser.dylib /Library/Java/Extensions/libsparser.dylib")
    
