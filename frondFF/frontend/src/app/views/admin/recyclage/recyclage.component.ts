import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup,Validators } from '@angular/forms';


interface RecyclingItem {
  title: string;
  content: string;
  videoUrl?: string;
}

@Component({
  selector: 'app-recyclage',
  templateUrl: './recyclage.component.html',
  styleUrls: ['./recyclage.component.css']
})
export class RecyclageComponent  implements OnInit{

  recyclingForm: FormGroup;
  recyclingItems: any[] = [];
  editMode = false;
  currentIndex: number | null = null;
  videoFile: File | null = null;

  constructor(private fb: FormBuilder) {
    this.recyclingForm = this.fb.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      videoUrl: ['']
    });
  }

  ngOnInit(): void {}

  onFileChange(event: any): void {
    if (event.target.files.length > 0) {
      this.videoFile = event.target.files[0];
    }
  }

  submitForm(): void {
    if (this.recyclingForm.valid) {
      const formValue = this.recyclingForm.value;
      if (this.editMode && this.currentIndex !== null) {
        this.recyclingItems[this.currentIndex] = { ...formValue, videoFile: this.videoFile };
      } else {
        this.recyclingItems.push({ ...formValue, videoFile: this.videoFile });
      }
      this.resetForm();
    }
  }

  editItem(index: number): void {
    this.editMode = true;
    this.currentIndex = index;
    const item = this.recyclingItems[index];
    this.recyclingForm.patchValue(item);
    this.videoFile = item.videoFile || null;
  }

  deleteItem(index: number): void {
    this.recyclingItems.splice(index, 1);
    this.resetForm();
  }

  resetForm(): void {
    this.editMode = false;
    this.currentIndex = null;
    this.recyclingForm.reset();
    this.videoFile = null;

    console.log("Veuillez remplir les champs.");
    alert("Veuillez remplir les champs.");
  }
}
