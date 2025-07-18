from flask import Flask, request, jsonify
from textblob import TextBlob

app = Flask(__name__)

emotion_keywords = {
    "confused": ["lost", "confused", "unclear", "don't know"],
    "confident": ["confident", "sure", "determined", "focused"],
    "anxious": ["worried", "anxious", "nervous", "afraid"],
    "sad": ["tired", "depressed", "hopeless", "sad"],
    "motivated": ["excited", "motivated", "ready", "inspired"]
}

def detect_emotion(text):
    blob = TextBlob(text)
    polarity = blob.sentiment.polarity
    confidence = "high" if polarity > 0.3 else "medium" if polarity > 0 else "low"

    detected_emotion = "neutral"
    lower_text = text.lower()
    for emotion, keywords in emotion_keywords.items():
        if any(kw in lower_text for kw in keywords):
            detected_emotion = emotion
            break

    return {
        "emotion": detected_emotion,
        "confidence": confidence,
        "polarity": polarity
    }

@app.route('/analyze', methods=['POST'])
def analyze():
    data = request.get_json()
    text = data.get("text", "")
    result = detect_emotion(text)
    return jsonify(result)


@app.route('/analyze/motivation', methods=['POST'])
def analyze_motivation():
    data = request.get_json()
    text = data.get("text", "").lower()

    # Simple keyword-based tone detection (replace with ML later)
    if "confused" in text or "lost" in text or "don't know" in text:
        tone = "confused"
        style = "mentor-guided"
    elif "excited" in text or "motivated" in text or "ready" in text:
        tone = "driven"
        style = "project-based"
    elif "anxious" in text or "scared" in text or "worried" in text:
        tone = "anxious"
        style = "video-based"
    else:
        tone = "neutral"
        style = "self-paced"

    return jsonify({
        "tone": tone,
        "suggested_style": style
    })
if __name__ == '__main__':
    app.run(debug=True, port=5000)

