# ☕ Coffee Machine Consumption — Neural Networks Project

## 🧠 Project Goal
This project models **energy disaggregation**: from total household power, it predicts the power used by a specific appliance (coffee machine).  
In simple terms, the model learns to separate one device’s signal from the full home signal.

## 🔬 Scientific Context
- Problem type: **sequence-to-sequence regression**
- Input: aggregated household consumption over time
- Output: coffee machine consumption over the same time window
- Dataset size used in the notebook/script: **50,000 sequences**, each with **40 time steps**

## ⚙️ Methodology
Three recurrent architectures are trained and compared:
- **Simple RNN**
- **LSTM**
- **GRU**

Main pipeline:
1. Load and visualize data
2. Normalize values to stabilize training
3. Split data into train/validation/test (70% / 15% / 15%)
4. Train each model with early stopping
5. Evaluate predictions with:
   - RMSE
   - MAE
   - Maximum absolute error

## 📊 Outputs in `out/`
The project includes visual results such as:
- Training history curves for each architecture
- Error-per-sequence plots (RMSE, MAE, Max Error)
- Qualitative test-sequence comparisons (model predictions vs ground truth)
- Initial signal visualizations (`plot_1`, `plot_2`, `plot_3`)

## 🗂️ Folder Highlights
- `code/comparingbasicrnnarchitectures.py`: full experimental pipeline in Python
- `code/ComparingBasicRNNArchitectures.ipynb`: notebook version of the same workflow
- `out/`: generated figures and evaluation plots
- PDF files: assignment brief and project report

## ✅ Key Takeaway
The project provides a complete and reproducible framework to compare recurrent neural network families for appliance-level power estimation, using clear quantitative metrics and visual diagnostics.
