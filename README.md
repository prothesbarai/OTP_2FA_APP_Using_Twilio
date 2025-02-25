# OTP 2FA App Using Twilio 🔐📱

Welcome to the **OTP 2FA App Using Twilio**! This project demonstrates how to implement two-factor authentication (2FA) for your app using Twilio's powerful API. With this app, you can securely verify users through OTP (One-Time Password) sent via SMS.

## 🚀 Features

- **Two-Factor Authentication (2FA)**: Adds an extra layer of security to your app by sending OTP to mobile numbers.
- **Twilio Integration**: Utilizes Twilio's API to send OTPs to user phone numbers.
- **Enable/Disable OTP Visibility**: Control the visibility of the OTP field based on user actions. ✔️🔒
- **Mobile-Friendly UI**: Designed using Android's XML layout system for a seamless experience on mobile devices.


## ⚙️ Setup Instructions

### Prerequisites
- **Twilio Account**: Sign up for a Twilio account [here](https://www.twilio.com/try-twilio) and obtain your `Account SID` and `Auth Token`.
- **Android Studio**: Make sure you have Android Studio installed to work with this project.

### Installation

**1. Clone the repository:**

```bash
git clone https://github.com/prothesbarai/OTP_2FA_APP_Using_Twilio.git
```
- **Implement Twilio in Gradle :**
```bash
implementation 'com.twilio:twilio:8.28.0'
```

## Configure Twilio:

Open the project and navigate to app/src/main/java/com/yourpackage/MainActivity.java.
Replace the placeholders for TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN, and your Twilio phone number in the code.
Build and Run:

Build the project by clicking on Build > Make Project.
Run the app on your emulator or connected Android device.

```java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class OTPSender {

    // Twilio Account SID এবং Auth Token এখানে যোগ করুন
    public static final String ACCOUNT_SID = "আপনার_TWILIO_ACCOUNT_SID";
    public static final String AUTH_TOKEN = "আপনার_TWILIO_AUTH_TOKEN";

    public static void sendOTPToMobile(String mobileNumber, String otp) {
        try {
            // Twilio এর জন্য সেটআপ
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            // OTP বার্তা তৈরি
            String message = "আপনার OTP হল: " + otp;

            // SMS পাঠানোর জন্য Twilio API ব্যবহার করা
            Message sms = Message.creator(
                    new PhoneNumber(mobileNumber), // প্রাপকের ফোন নম্বর
                    new PhoneNumber("আপনার_TWILIO_PHONE_NUMBER"), // আপনার Twilio ফোন নম্বর
                    message // বার্তা
            ).create();

            // সফল হলে বার্তা প্রদর্শন
            JOptionPane.showMessageDialog(null, "OTP সফলভাবে পাঠানো হয়েছে!", "সফলতা", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "OTP পাঠানোর সময় সমস্যা হয়েছে!", "ত্রুটি", JOptionPane.ERROR_MESSAGE);
        }
    }
}
```

## 🧑‍💻 Contributing
We welcome contributions! If you'd like to improve or add new features, please follow these steps:

- Fork the repository.
- Create a new branch for your changes.
- Commit your changes.
- Push your changes and create a pull request.



### Key Highlights:
- **Professional Formatting**: Clear headings, bullet points, and a section on contributing.
- **Powerful Emojis**: To keep it engaging.
- **Screenshots**: Showing key UI elements from the links you provided.
- **Twilio Integration**: Clear instructions on setting up Twilio and configuring the project. 

Feel free to customize it with your name or any additional details you want to add!



## License 📜
This project is licensed under the MIT License - see the LICENSE file for details.

## 📷 Screenshots

### Enable and Disable OTP Field Visibility
![Enable and Disable OTP Field Visibility](https://github.com/prothesbarai/OTP_2FA_APP_Using_Twilio/blob/main/Enable%20And%20Disable%20Depend%20On%20VISIBILITY.png)

### Logo
![Logo](https://github.com/prothesbarai/OTP_2FA_APP_Using_Twilio/blob/main/logo.jpg)

## Author 👨‍💻
- [Prothes](https://github.com/prothesbarai)