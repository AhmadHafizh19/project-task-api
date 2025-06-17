### 🧩 **Challenge 4: Task & Project Management**

**Repository:** `project-task-api`

#### 📌 Product Requirement:

Manajer ingin membuat proyek dan menambahkan tugas dalam proyek tersebut.

#### 🧠 Entities:

- `Project`: `id`, `name`, `deadline`
- `Task`: `id`, `projectId`, `description`, `status`

#### 🔗 API Endpoints:

1. `POST /projects` – Buat proyek
2. `POST /projects/{id}/tasks` – Tambahkan task ke proyek
3. `GET /projects/{id}/tasks` – Lihat semua task dalam proyek
4. `PATCH /tasks/{id}/status` – Ubah status task

#### ✅ Validation:

- Status hanya: `TODO`, `IN_PROGRESS`, `DONE`
- Deadline tidak boleh tanggal lampau
