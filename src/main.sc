require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Uzb - 1
            Ru - 2
            Eng - 3
        buttons:
            "1 - Uzb" -> /Salom
            "2 - Rus" -> /Привет
            "3 - Eng" -> /Hello
        AIAgent: 
            id = translator_agent
            model = gpt-4o
            temperature = 0.6
            topP = 1
            frequencyPenalty = 0
            presencePenalty = 0
            maxTokens = 4000
            role = Professional translator
            goal = Translate the text that the user sends you
            instructions = Find out what language to translate the text into. Suggest three translations. Ask if the user likes the translation. If necessary, generate new variants
            requiredData = 
            context = 
            functions = [""]
            llmClassificationEnabled = false
            intentConfidence = 
            chatHistoryEnabled = false
            chatHistoryLimit = 50
            knowledgeBase = 
            knowledgeBaseConfidence = 0.8

    state: Salom
        intent!: /привет
        a: Salom! Men bank xizmatlari bo‘yicha yordam beraman. Hisob ma’lumotlari uchun 1, kredit haqida ma’lumot uchun 2, savol bering uchun 3 ni bosing.
        q: 1 || toState = "/Uzb - 1"
        q: 2 || toState = "/uzb - 2"
        q: 3 || toState = "/Uzb - 3"

    state: Bye
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Uzb - 1
        event!: match
        a: Hisob ma’lumotlari uchun bank filialiga murojaat qiling yoki online bankingdan foydalaning. Yana savol bormi?

    state: Привет
        intent!: /привет
        a: Здравствуйте! Я помогу вам с банковскими услугами. Нажмите 1 для информации о счете, 2 для информации о кредите, 3 для вопросов.
        q: 1 || toState = "/Ru - 1"
        q: 2 || toState = "/Ru - 2"
        q: 3 || toState = "/Ru - 3"

    state: Hello
        intent!: /привет
        a: Hello! I will help you with banking services. Press 1 for account information, 2 for loan information, 3 for questions.
        q: 1 || onlyThisState = false, toState = "/Eng - 1"
        q: 2 || toState = "/Eng - 2"
        q: 3 || toState = "/Eng- 3"

    state: uzb - 2
        event!: match
        a: Kredit bo‘yicha ma’lumot olish uchun 998 55 501 01 01 raqamiga qo‘ng‘iroq qiling. Yana savol bormi

    state: Uzb - 3
        event!: match
        a: Savolingizni yozing, tez orada javob beraman!

    state: Ru - 1
        event!: match
        a: Для получения информации о счете обратитесь в отделение вашего банка или воспользуйтесь онлайн-банкингом. Есть еще вопросы?

    state: Ru - 2
        event!: match
        a: Для получения информации о счете обратитесь в отделение вашего банка или воспользуйтесь онлайн-банкингом. Есть еще вопросы?

    state: Ru - 3
        event!: match
        a: Для получения информации о счете обратитесь в отделение вашего банка или воспользуйтесь онлайн-банкингом. Есть еще вопросы?

    state: Eng - 1
        event!: match
        a: Для получения информации о счете обратитесь в отделение вашего банка или воспользуйтесь онлайн-банкингом. Есть еще вопросы?

    state: Eng - 2
        event!: match
        a: Для получения информации о счете обратитесь в отделение вашего банка или воспользуйтесь онлайн-банкингом. Есть еще вопросы?

    state: Eng- 3
        event!: match
        a: Для получения информации о счете обратитесь в отделение вашего банка или воспользуйтесь онлайн-банкингом. Есть еще вопросы?