responses = {
    r'I am (.*)': [
        "Why are you {0}?",
        "How long have you been {0}?",
        "Do you enjoy being {0}?"
    ],
    r'My name is (.*)': [
        "Nice to meet you, {0}!",
        "Hello {0}, how can I help you today?"
    ],
    r'.*feel.*': [
        "How does that make you feel?",
        "Why do you feel that way?"
    ],
    r'.*why.*': [
        "Why do you say that?",
        "Can you elaborate on this?"
    ],
    r'.*hello.*': [
        "Hello! How can I assist you today?",
        "Hi there! What would you like to talk about?"
    ],
    r'.*help.*': [
        "How can I help you?",
        "What do you need assistance with?"
    ],
    r'.*name.*': [
        "My name is ELIZA. What's yours?",
        "I am ELIZA, your virtual assistant. And you?"
    ],
    r'.*day.*': [
        "The day is going well, thank you for asking!",
        "It's a good day so far, how about yours?"
    ],
    r'.*': [
        "Please tell me more about that.",
        "That's very interesting. Go on.",
        "I see. And what do you think about it?"
    ]
}

responsesGR = {
    r'Είμαι (.*)': [
        "Γιατί είσαι {0};",
        "Πόσο καιρό νιώθεις {0};",
        "Σου αρέσει να είσαι {0};"
    ],
    r'Το όνομά μου είναι (.*)': [
        "Χάρηκα, {0}!",
        "Γεια σου {0}, πώς μπορώ να σε βοηθήσω σήμερα;"
    ],
    r'.*νιώθ.*': [
        "Πώς σε κάνει να νιώθεις αυτό;",
        "Γιατί νιώθεις έτσι;"
    ],
    r'.*γιατί.*': [
        "Γιατί το λες αυτό;",
        "Μπορείς να το εξηγήσεις περισσότερο;"
    ],
    r'.*γεια.*': [
        "Γεια σου! Πώς μπορώ να σε βοηθήσω σήμερα;",
        "Γεια! Τι θα ήθελες να συζητήσουμε;"
    ],
    r'.*βοήθεια.*': [
        "Πώς μπορώ να σε βοηθήσω;",
        "Με τι χρειάζεσαι βοήθεια;"
    ],
    r'.*όνομα.*': [
        "Το όνομά μου είναι ELIZA. Ποιο είναι το δικό σου;",
        "Είμαι η ELIZA, η εικονικός βοηθός σου. Εσένα πως σε λένε;"
    ],
    r'.*ημέρα.*': [
        "Η μέρα πηγαίνει καλά, ευχαριστώ που ρωτάς!",
        "Είναι μια καλή μέρα μέχρι στιγμής, εσύ πως είσαι;"
    ],
    r'.*': [
        "Παρακαλώ πες μου περισσότερα γι' αυτό.",
        "Αυτό είναι πολύ ενδιαφέρον. Συνέχισε.",
        "Βλέπω. Και τι σκέφτεσαι γι' αυτό;"
    ]
}