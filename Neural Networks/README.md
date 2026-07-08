# 🧠 Neural Networks Projects Overview

This folder contains two academic deep learning projects with different scientific goals:

## 1) ☕ Coffee Machine Consumption
**Focus:** time-series energy disaggregation (sequence-to-sequence regression).

- Uses recurrent architectures (**Simple RNN, LSTM, GRU**) to estimate coffee machine power from aggregated household power.
- Applies normalization, train/validation/test split, and early stopping.
- Evaluates models with **RMSE, MAE, and Max Error**.
- Includes multiple visual outputs for training behavior and prediction quality.

📁 Path: `/home/runner/work/Uni-Projects/Uni-Projects/Neural Networks/Coffee Machine Consumption`

---

## 2) 👁️ Computer Vision
**Focus:** image classification and transfer learning analysis.

- Compares **DNN vs CNN** on MNIST.
- Studies **transfer learning** from MNIST to Fashion-MNIST.
- Uses robust evaluation with cross-validation and classification metrics (accuracy, precision, recall, F1-score).
- Shows that CNN-based approaches are generally stronger for image data, with small gains from transfer learning in the CNN setting.

📁 Path: `/home/runner/work/Uni-Projects/Uni-Projects/Neural Networks/Computer Vision`

---

## 🔗 Combined Scientific Message
Together, the two projects show how neural network design must match data structure:
- **Temporal signals** → recurrent models (RNN/LSTM/GRU)
- **Images** → convolutional models (CNN)

In both cases, careful preprocessing, controlled evaluation, and clear metrics are essential for reliable conclusions. ✅
