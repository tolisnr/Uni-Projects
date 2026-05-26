# Neural Networks and Computer Vision Project

## Overview

This project investigates and compares different neural network architectures for computer vision tasks, specifically focusing on image classification using deep learning. The project consists of two main experiments that progressively build upon each other:

1. **Comparing Basic Neural Network Architectures** on the MNIST dataset
2. **Evaluating Transfer Learning Usability** on the Fashion-MNIST dataset

## Project Objectives

The primary goals of this project are to:

- Compare the performance of Deep Neural Networks (DNNs) vs. Convolutional Neural Networks (CNNs) on image classification
- Evaluate the effectiveness of transfer learning in computer vision
- Understand how pre-trained models can be adapted to new domains with different datasets
- Implement robust evaluation methodologies using K-Fold cross-validation
- Analyze performance metrics (accuracy, precision, recall, F1-score) across different architectures

## Project Structure

```
├── code/
│   ├── comparingbasicnnarchitectures.py      # Experiment 1: DNN vs CNN comparison
│   ├── ComparingBasicNNArchitectures.ipynb   # Jupyter notebook for Experiment 1
│   ├── evaluatingtransferlearningusability.py # Experiment 2: Transfer learning evaluation
│   └── evaluatingTransferLearningUsability.ipynb # Jupyter notebook for Experiment 2
├── out/
│   ├── best_dnn_model.h5                     # Best DNN model from Experiment 1
│   ├── best_cnn_model.h5                     # Best CNN model from Experiment 1
│   ├── erotima1.csv                          # Results from Experiment 1
│   ├── erotima2.csv                          # Results from Experiment 2
│   ├── cnn-vs-dnn.png                        # Comparison visualization
│   ├── fashion_mnist_images.png              # Dataset visualization
│   ├── mnist_samples.png                     # Dataset visualization
│   └── perf_comp.png                         # Performance comparison chart
└── README.md                                  # This file
```

## Experiment 1: Comparing Basic Neural Network Architectures

### Dataset
- **MNIST (Modified National Institute of Standards and Technology)**
  - 60,000 training samples
  - 10,000 testing samples
  - 28x28 pixel grayscale handwritten digit images
  - 10 classes (digits 0-9)

### Methodology

**K-Fold Cross-Validation**
- Strategy: Stratified K-Fold with 6 splits
- Purpose: Provides robust performance estimation with multiple train-validation-test combinations
- Each fold uses 50,000 samples for training and 10,000 for validation

**Data Preprocessing**
- Normalization: Pixel values scaled from [0, 255] to [0, 1]
- Channel expansion: 2D images (28×28) converted to 3D format (28×28×1)
- Label encoding: One-hot encoding for 10 classes

### Architecture Comparison

#### Deep Neural Network (DNN)
- Flatten layer: Converts 28×28×1 image to 784-dimensional vector
- Hidden layers:
  - First dense layer: 128 neurons with ReLU activation
  - Dropout layer: 20% dropout for regularization
  - Second dense layer: 64 neurons with ReLU activation
- Output layer: 10 neurons with softmax activation
- Optimizer: Adam
- Loss function: Categorical cross-entropy

#### Convolutional Neural Network (CNN)
- Convolutional layer 1: 6 filters, 3×3 kernel, ReLU activation
- Max pooling layer 1: 2×2 pool size
- Convolutional layer 2: 12 filters, 3×3 kernel, ReLU activation
- Max pooling layer 2: 2×2 pool size
- Flatten layer: Reduces feature maps to 1D vector
- Dense layer: 128 neurons with ReLU activation
- Dropout layer: 25% dropout for regularization
- Output layer: 10 neurons with softmax activation
- Optimizer: Adam
- Loss function: Categorical cross-entropy

### Key Results - Experiment 1

The results clearly demonstrate the superiority of CNNs for image classification tasks:

**CNN Performance (Test Set, Average across 6 Folds)**
- Accuracy: ~98.4%
- Precision: ~98.4%
- Recall: ~98.4%
- F1-Score: ~98.4%

**DNN Performance (Test Set, Average across 6 Folds)**
- Accuracy: ~97.4%
- Precision: ~97.4%
- Recall: ~97.4%
- F1-Score: ~97.4%

**Key Finding**: CNNs consistently outperform DNNs by approximately 1.0 percentage point in accuracy, demonstrating their superior capability to capture spatial patterns in images through convolutional filters.

## Experiment 2: Evaluating Transfer Learning Usability

### Dataset
- **Fashion-MNIST**
  - 60,000 training samples
  - 10,000 testing samples
  - 28×28 pixel grayscale images of clothing items
  - 10 classes (T-shirt, Trouser, Pullover, Dress, Coat, Sandal, Shirt, Sneaker, Bag, Ankle boot)

### Transfer Learning Strategy

This experiment evaluates whether models pre-trained on MNIST (digits) can effectively transfer learning to Fashion-MNIST (clothing items), despite the different nature of the datasets.

**Two-Phase Training Approach**

**Phase 1: Frozen Feature Extraction (40 epochs)**
- Load best DNN/CNN models from Experiment 1
- Freeze all layers except the output layer
- Train only the final classification layer on Fashion-MNIST
- This approach leverages learned features from MNIST digits

**Phase 2: Fine-Tuning (20 epochs)**
- Unfreeze all layers in the model
- Continue training end-to-end on Fashion-MNIST
- Allow all weights to adapt to the new dataset
- Uses lower learning rate (implicit in continued Adam optimization)

### Techniques Compared in Experiment 2

1. **DNN from Scratch**: Fresh DNN trained on Fashion-MNIST (150 epochs)
2. **DNN Transfer Learning**: Best DNN from Exp. 1 adapted to Fashion-MNIST
3. **CNN from Scratch**: Fresh CNN trained on Fashion-MNIST (150 epochs)
4. **CNN Transfer Learning**: Best CNN from Exp. 1 adapted to Fashion-MNIST

### Key Results - Experiment 2

**CNN from Scratch (Test Set, Average across 6 Folds)**
- Accuracy: ~89.6%
- Precision: ~89.5%
- Recall: ~89.6%
- F1-Score: ~89.6%

**CNN Transfer Learning (Test Set, Average across 6 Folds)**
- Accuracy: ~89.9%
- Precision: ~89.9%
- Recall: ~89.9%
- F1-Score: ~89.9%

**DNN from Scratch (Test Set, Average across 6 Folds)**
- Accuracy: ~88.9%
- Precision: ~89.0%
- Recall: ~88.9%
- F1-Score: ~88.9%

**DNN Transfer Learning (Test Set, Average across 6 Folds)**
- Accuracy: ~88.9%
- Precision: ~89.0%
- Recall: ~88.9%
- F1-Score: ~88.9%

**Key Findings**:
1. **CNN Transfer Learning shows marginal improvement (~0.3%) over CNN from Scratch**, confirming that transfer learning can provide benefits even with different image domains
2. **DNN Transfer Learning performs comparably to DNN from Scratch**, suggesting that architectural factors (CNN vs DNN) matter more than transfer learning for this specific domain gap
3. **CNNs consistently outperform DNNs** across both training approaches (from scratch and transfer learning), reinforcing the architectural advantage of convolutional approaches for image data

## Technical Implementation

### Technologies & Libraries
- **Deep Learning Framework**: TensorFlow/Keras
- **Data Handling**: NumPy, Scikit-Learn
- **Model Evaluation**: Scikit-Learn metrics (accuracy, precision, recall, F1-score)
- **Visualization**: Matplotlib, Seaborn
- **Data Management**: Pandas

### Training Configuration
- **Optimizer**: Adam (adaptive learning rate)
- **Loss Function**: Categorical Cross-Entropy (multi-class classification)
- **Batch Size**: 128 samples
- **Activation Functions**:
  - Hidden layers: ReLU (Rectified Linear Unit)
  - Output layer: Softmax (for probability distribution)

### Model Persistence
- Best models saved in HDF5 format (.h5)
  - `best_dnn_model.h5`: DNN with best test accuracy from Experiment 1
  - `best_cnn_model.h5`: CNN with best test accuracy from Experiment 1
- These models are loaded and adapted for transfer learning in Experiment 2

## Results Summary

### Comparative Performance Metrics

| Architecture | Dataset | Strategy | Avg Accuracy |
|---|---|---|---|
| DNN | MNIST | K-Fold (Exp 1) | ~97.4% |
| CNN | MNIST | K-Fold (Exp 1) | ~98.4% |
| DNN | Fashion-MNIST | From Scratch | ~88.9% |
| DNN | Fashion-MNIST | Transfer Learning | ~88.9% |
| CNN | Fashion-MNIST | From Scratch | ~89.6% |
| CNN | Fashion-MNIST | Transfer Learning | ~89.9% |

## Key Conclusions

1. **Convolutional Neural Networks (CNNs) are Superior for Image Classification**
   - CNNs leverage spatial locality and hierarchical feature extraction
   - DNNs treat images as flat vectors, losing spatial information
   - Performance gap: ~1% on MNIST, consistent across experiments

2. **Transfer Learning Effectiveness**
   - Transfer learning provides meaningful benefits for CNN architectures across different image domains
   - The pre-learned convolutional filters from MNIST generalize well to Fashion-MNIST
   - Frozen phase (feature extraction) + Fine-tuning phase together create an effective approach

3. **Architecture Choice Matters More Than Training Strategy**
   - For this project, the choice of CNN vs. DNN has greater impact on performance than using transfer learning
   - However, transfer learning still offers computational advantages (fewer epochs needed)

4. **Robust Evaluation Methodology**
   - K-Fold cross-validation provides reliable performance estimates
   - Average metrics across folds reduce variance and overfitting concerns
   - Comprehensive metrics (accuracy, precision, recall, F1) provide fuller picture of model performance

## Deliverables

- **Training Scripts**: Python implementations of both experiments
- **Jupyter Notebooks**: Interactive notebooks with visualizations and explanations
- **Trained Models**: Best DNN and CNN models saved for reproducibility
- **Results Data**: CSV files with detailed metrics for all folds
- **Visualizations**: Performance comparison charts and dataset samples

## Files Description

- **comparingbasicnnarchitectures.py**: Complete implementation of Experiment 1 with model definitions, K-Fold training, and evaluation
- **evaluatingtransferlearningusability.py**: Complete implementation of Experiment 2 with transfer learning pipeline and comparative analysis
- **erotima1.csv**: Contains rows for each fold and technique (DNN/CNN) with train/test metrics
- **erotima2.csv**: Contains rows for each fold and technique (DNN_from_scratch, CNN_from_scratch, DNN_transfer, CNN_transfer) with metrics
- **cnn-vs-dnn.png**: Bar chart comparison of DNN vs CNN accuracy and F1-score from Experiment 1
- **perf_comp.png**: Bar chart comparison of all four techniques in Experiment 2
- **fashion_mnist_images.png**: Grid visualization of Fashion-MNIST dataset samples
- **mnist_samples.png**: Grid visualization of MNIST dataset samples

## Usage & Reproducibility

### Running Experiment 1
```bash
python comparingbasicnnarchitectures.py
```

### Running Experiment 2
```bash
python evaluatingtransferlearningusability.py
```

Both scripts will:
1. Load and preprocess the respective datasets
2. Execute K-Fold cross-validation with 6 splits
3. Train DNN and CNN architectures
4. Save best models to disk
5. Export results to CSV
6. Generate performance visualization charts

## References

- LeCun, Y., & Cortes, C. (1998). The MNIST Database of Handwritten Digits
- Xiao, H., Rasul, K., & Vollgraf, R. (2017). Fashion-MNIST: A Novel Image Dataset for Benchmarking Machine Learning Algorithms
- Chollet, F., et al. (2015). Keras: Deep Learning Library for Python
- Yosinski, J., Clune, J., Bengio, Y., & Lipson, H. (2014). How Transferable are Features in Deep Neural Networks?

---

**Project Type**: Academic Research  
**Implementation**: Deep Learning with TensorFlow/Keras  
**Evaluation Method**: K-Fold Cross-Validation (6 Splits)  
**Metrics**: Accuracy, Precision, Recall, F1-Score  
**Status**: Completed

