import re
import random
from dictionary import responses, responsesGR


def get_response(user_input, lang='EN'): #προεπιλεγμένη γλώσσα Αγγλικά
    if lang == 'GR':
        resp_dict = responsesGR # Λεξικό απαντήσεων στα ελληνικά    
    else:
        resp_dict = responses

    # Για κάθε μοτίβο (pattern) και λίστα απαντήσεων στο λεξικό απαντήσεων
    for pattern, resp_list in resp_dict.items():
        # Ελέγχει αν το μοτίβο ταιριάζει με την είσοδο του χρήστη (αγνοεί κεφαλαία/πεζά)
        match = re.search(pattern, user_input, re.IGNORECASE)
        if match:
            # Επιλέγει τυχαία μία απάντηση από τη λίστα
            response = random.choice(resp_list)
            # Αν υπάρχουν ομάδες που ταιριάζουν στο μοτίβο, τις ενσωματώνει στην απάντηση
            if match.groups():
                return response.format(*match.groups())
            # Επιστρέφει την απάντηση αν δεν υπάρχουν ομάδες
            return response
    # Επιστρέφει προεπιλεγμένο μήνυμα αν δεν βρέθηκε ταίριασμα
    return "I'm not sure I understand you."


if __name__ == "__main__":
    lang = input("Choose language (EN/GR): ").strip().upper()
    print("Type 'quit' to exit.")
    while True:
        user_input = input("You: ")
        if user_input.lower() == "quit":
            print("ELIZA: Goodbye!")
            break
        print("ELIZA:", get_response(user_input, lang))
