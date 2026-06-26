# Python program for implementation of Merge Sort

def merge(arr, l, m, r):
    n1 = m - l + 1
    n2 = r - m
    
    # Create temp arrays
    L = [0] * n1
    R = [0] * n2
    
    # Copy data to temp arrays L[] and R[]
    for i in range(n1):
        L[i] = arr[l + i]
    
    for j in range(n2):
        R[j] = arr[m + 1 + j]
    
    # Merge the temp arrays back into arr[l..r]
    i = 0     # Initial index of first subarray
    j = 0     # Initial index of second subarray
    k = l     # Initial index of merged subarray
    
    while i < n1 and j < n2:
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1
    
    # Copy the remaining elements of L[], if there are any
    while i < n1:
        arr[k] = L[i]
        i += 1
        k += 1
    
    # Copy the remaining elements of R[], if there are any
    while j < n2:
        arr[k] = R[j]
        j += 1
        k += 1

def merge_sort(arr, l, r):
    if l < r:
        # Same as (l+r)//2, but avoids overflow for large l and r
        m = l + (r - l) // 2
        
        # Sort first and second halves
        merge_sort(arr, l, m)
        merge_sort(arr, m + 1, r)
        
        merge(arr, l, m, r)

# Function to print the array
def print_array(arr):
    for i in range(len(arr)):
        print(f"{arr[i]}", end=" ")
    print()

# Driver code
if __name__ == "__main__":
    arr = [12, 11, 13, 5, 6, 7]
    print("Given array is:")
    print_array(arr)
    
    merge_sort(arr, 0, len(arr) - 1)
    
    print("\nSorted array is:")
    print_array(arr)
